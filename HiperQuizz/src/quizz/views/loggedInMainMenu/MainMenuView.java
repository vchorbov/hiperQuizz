package quizz.views.loggedInMainMenu;

import quizz.dao.QuizRepository;
import quizz.dao.UserRepository;
import quizz.exception.EntityAlreadyExistsException;
import quizz.model.Player;
import quizz.model.Quiz;
import quizz.model.User;
import quizz.util.creators.QuizCreator;
import quizz.util.creators.UserCreator;
import quizz.util.printers.QuizTablePrinter;
import quizz.util.printers.UsersTablePrinter;
import quizz.validations.InputValidator;

import java.util.Scanner;

public class MainMenuView {
    public static void exposeMainMenu(Scanner scanner,
                                      UserRepository playerRepo,
                                      UserRepository adminRepo,
                                      QuizRepository quizRepo,
                                      User tempPlayer
                                          ){
        InputValidator inValidator = new InputValidator();
        MainMenuBar.printMainMenu();
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
            MainMenuBar.printMainMenu();
            input = scanner.nextLine();
            //validate the input
            command = Integer.parseInt(input);

        }

    }
}
