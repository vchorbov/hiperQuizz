package quizz;

import quizz.commands.LoadEntitiesCommand;
import quizz.commands.SaveEntitiesCommand;
import quizz.controller.Register;
import quizz.dao.QuizRepository;
import quizz.dao.QuizResultRepository;
import quizz.dao.UserRepository;
import quizz.dao.impl.LongKeyGenerator;
import quizz.dao.impl.QuizMemoryImpl;
import quizz.dao.impl.QuizResultMemoryImpl;
import quizz.dao.impl.UserMemoryImpl;
import quizz.exception.EntityAlreadyExistsException;
import quizz.model.*;
import quizz.util.creators.QuizCreator;
import quizz.util.printers.QuizTablePrinter;
import quizz.util.printers.UsersTablePrinter;
import quizz.util.creators.UserCreator;
import quizz.validations.*;
import quizz.views.MainMenuView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator inValidator = new InputValidator();

        //take it out of here
        UserMemoryImpl playerRepo = new UserMemoryImpl(new LongKeyGenerator());
        UserRepository adminRepo = new UserMemoryImpl(new LongKeyGenerator());
        QuizRepository quizRepo = new QuizMemoryImpl(new LongKeyGenerator());
        QuizResultRepository quizResultRepo = new QuizResultMemoryImpl(new LongKeyGenerator());

        //fill the repo with the sample data
        Arrays.stream(Register.SAMPLE_PLAYERS).sequential().forEach(player -> {
            try {
                playerRepo.create(player);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });
        Arrays.stream(Register.SAMPLE_ADMINS).sequential().forEach(player -> {
            try {
                adminRepo.create(player);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });
        Arrays.stream(Register.SAMPLE_QUIZZES).sequential().forEach(quiz -> {
            try {
                quizRepo.create(quiz);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        // temporary User for the creation of new Quizzes
        Player tempPlayer = new Player(122L,"spy", "spy@gmail.com", "????", Gender.MALE);

        // SAMPLE_DATA
        List<User> players = new ArrayList<>(List.of(Register.SAMPLE_PLAYERS));
        List<User> admins = new ArrayList<>(List.of(Register.SAMPLE_ADMINS));
        List<Question> questions = new ArrayList<>(List.of(Register.SAMPLE_QUESTIONS));
        List<Answer> answers = new ArrayList<>(List.of(Register.SAMPLE_ANSWERS));
        List<Quiz> quizzes = new ArrayList<>(List.of(Register.SAMPLE_QUIZZES));


        MainMenuView.printMainMenu();
        String input = scanner.nextLine();

        // Insert a valid command

            while(!inValidator.isANumber(input) || !inValidator.isInRange(input, 8)) {
                System.out.println("Please inset a valid number in the range from 0 to " + (8));
                input = scanner.nextLine();
            }
            int command = Integer.parseInt(input);

            while (command != 0) {
                switch (command) {
                    case 1: //list all players
                        UsersTablePrinter.printTableForUsers(playerRepo);
                        break;
                    case 2: //list all admins
                        UsersTablePrinter.printTableForUsers(adminRepo);
                        break;
                    case 3: // list all quizzes
                        QuizTablePrinter.printTableForUsers(quizRepo);
                        break;
                    case 4: // list dashboard
                        break;
                    case 5: // add new player
                        User playerToBeCreated = UserCreator.createNewPlayer(scanner);
                        if (playerToBeCreated != null) {
                            try {
                                playerRepo.create(playerToBeCreated);
                            } catch (EntityAlreadyExistsException e) {
                                System.err.println("A player with this credentials already exists.");
                            }
                        }
                        break;
                    case 6: // add new admin
                        User adminToBeCreated = UserCreator.createNewAdmin(scanner);
                        if (adminToBeCreated != null) {
                            try {
                                adminRepo.create(adminToBeCreated);
                            } catch (EntityAlreadyExistsException e) {
                                System.err.println("An admin with this credentials already exists.");
                            }
                        }
                        break;
                    case 7: // add new quiz
                        Quiz quizToBeCreated = QuizCreator.createNewQuiz(scanner, tempPlayer);
                        try {
                            quizRepo.create(quizToBeCreated);
                        } catch (EntityAlreadyExistsException e) {
                            System.err.println("A quiz on the same subject already exists.");
                        }
                        break;
                    case 8:
                        break;
                    case 9:

                    default:
                        System.out.println("Please inset a valid number in the range from 0 to " + (8));
                }
                MainMenuView.printMainMenu();
                input = scanner.nextLine();
                //validate the input
                command = Integer.parseInt(input);

            }
        // Testing serialization/deserialization to /from file
        try {
            SaveEntitiesCommand saveCommand = new SaveEntitiesCommand(new FileOutputStream("quizzes.db"),
                    playerRepo, quizRepo
                    , quizResultRepo);
            System.out.println(saveCommand.execute());
            playerRepo.drop();
            quizRepo.drop();
            quizResultRepo.drop();

            LoadEntitiesCommand loadCommand = new LoadEntitiesCommand(new FileInputStream("quizzes.db"),
                    playerRepo, quizRepo, quizResultRepo);
            //NullPointerEx
            System.out.println(loadCommand.execute());


            UsersTablePrinter.printTableForUsers(playerRepo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        }


    }

