package quizz;

import quizz.commands.LoadEntitiesCommand;
import quizz.commands.SaveEntitiesCommand;
import quizz.dao.QuizRepository;
import quizz.dao.QuizResultRepository;
import quizz.dao.UserRepository;
import quizz.dao.impl.LongKeyGenerator;
import quizz.dao.impl.QuizMemoryImpl;
import quizz.dao.impl.QuizResultMemoryImpl;
import quizz.dao.impl.UserMemoryImpl;
import quizz.model.*;
import quizz.views.loggedInMainMenu.MainMenuView;
import quizz.views.noUserLoggedInMenu.NoUserView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //create the repos
        UserMemoryImpl playerRepo = new UserMemoryImpl(new LongKeyGenerator());
        UserRepository adminRepo = new UserMemoryImpl(new LongKeyGenerator());
        QuizRepository quizRepo = new QuizMemoryImpl(new LongKeyGenerator());
        QuizResultRepository quizResultRepo = new QuizResultMemoryImpl(new LongKeyGenerator());
      // User loggedInUser =  new Player("spy", "spy@gmail.com", "????", Gender.MALE);

        //retrieve data form file
        LoadEntitiesCommand loadCommand = null;
        try {
            loadCommand = new LoadEntitiesCommand(new FileInputStream("quizzes.db"),
                    playerRepo, quizRepo, quizResultRepo);
            System.out.println(loadCommand.execute());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // for testing purposes here♥
        playerRepo.findAll().forEach(System.out::println);

        // User of the session
        User loggedInUser = new Player();

        //Menu For Users yet to log in
        do{
            loggedInUser = NoUserView.exposeLoginMenu(scanner, playerRepo);
        }while(loggedInUser==null || loggedInUser.getUsername().isBlank());


        //Menu For Logged Users
        MainMenuView.exposeMainMenu(scanner, playerRepo, adminRepo, quizRepo, (Player) loggedInUser);

        // Save the created entities in the flat db and clean locally
        try {
            SaveEntitiesCommand saveCommand = new SaveEntitiesCommand(new FileOutputStream("quizzes.db"),
                    playerRepo, quizRepo
                    , quizResultRepo);
            System.out.println(saveCommand.execute());
            playerRepo.drop();
            quizRepo.drop();
            quizResultRepo.drop();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        }
    }

