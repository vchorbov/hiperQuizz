package quizz.views.loggedInMainMenu;

import quizz.dao.QuizRepository;
import quizz.dao.UserRepository;
import quizz.exception.EntityAlreadyExistsException;
import quizz.model.Player;
import quizz.model.Quiz;
import quizz.model.User;
import quizz.util.creators.QuizCreator;
import quizz.util.creators.UserCreator;
import quizz.util.printers.DashboardPrinter;
import quizz.util.printers.QuizTablePrinter;
import quizz.util.printers.UsersTablePrinter;
import quizz.validations.InputValidator;

import java.util.Scanner;

public class MainMenuView {
    public static void exposeMainMenu(Scanner scanner,
                                      UserRepository playerRepo,
                                      UserRepository adminRepo,
                                      QuizRepository quizRepo,
                                      Player tempPlayer
                                          ){
        InputValidator inValidator = new InputValidator();
        MainMenuBar.printMainMenu();
        String input = scanner.nextLine();

        // Insert a valid command

        while(!inValidator.isANumber(input) || !inValidator.isInRange(input, 6)) {
            System.out.println("Please inset a valid number in the range from 0 to " + (6));
            input = scanner.nextLine();
        }
        int command = Integer.parseInt(input);

        while (command != 0) {
            switch (command) {
                case 1: //list all players
                    UsersTablePrinter.printTableForUsers(playerRepo);
                    break;
                case 2: // list all quizzes
                    QuizTablePrinter.printTableForUsers(quizRepo);
                    break;
                case 3: // add new player
                    User playerToBeCreated = UserCreator.createNewPlayer(scanner);
                    if (playerToBeCreated != null) {
                        try {
                            playerRepo.create(playerToBeCreated);
                        } catch (EntityAlreadyExistsException e) {
                            System.err.println("A player with this credentials already exists.");
                        }
                    }
                    break;
                case 4: // add new quiz
                    Quiz quizToBeCreated = QuizCreator.createNewQuiz(scanner, tempPlayer);
                    try {
                        quizRepo.create(quizToBeCreated);
                    } catch (EntityAlreadyExistsException e) {
                        System.err.println("A quiz on the same subject already exists.");
                    }
                    break;
                case 5: // participate in a quiz
                    // ! ! !Happy path only! ! !
                    QuizTablePrinter.printTableForUsers(quizRepo);
                    System.out.println("These are all the quizzes to choose from. Select one by it's id.");
                    input = scanner.nextLine();
                    Long quizId = Long.parseLong(input);
                    Quiz quizToPlay = quizRepo.findById(quizId).orElse(null);
                    if(quizToPlay == null){
                        System.out.println("Invalid id");
                    }else{
                        String textQ = quizToPlay.getQuestions().get(0).getText();
                        String AnswerToQ = quizToPlay.getQuestions().get(0).getAnswers().get(0).getText();
                        System.out.println(textQ);
                        System.out.println("Type your answer here:");
                        input = scanner.nextLine();
                        if(input.equals(AnswerToQ)){
                            tempPlayer.setOverallScore(tempPlayer.getOverallScore()
                                    + quizToPlay
                                    .getQuestions()
                                    .get(0)
                                    .getAnswers()
                                    .get(0)
                                    .getScore());
                            System.out.println("Congrats! That was correct!");
                        }else{
                            System.out.println("Sorry! Better Luck next time!");
                        }
                    }
                    break;
                case 6: // list dashboard
                    DashboardPrinter.printTableForUsers(playerRepo);
                    break;
                default:
                    System.out.println("Please inset a valid number in the range from 0 to " + (6));
            }
            MainMenuBar.printMainMenu();
            input = scanner.nextLine();
            //validate the input
            command = Integer.parseInt(input);

        }

    }
}
