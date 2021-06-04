package quizz;

import quizz.controller.Register;
import quizz.model.*;
import quizz.util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String MAIN_MENU = "           M A I N    M E N U";
    private static final String delimeter = "=========================================";
    private static final List<String> menu = new ArrayList<>(List.of(
            "<0> EXIT from this program ",
            "<1> List all players ",
            "<2> List all admins",
            "<3> List all questions",
            "<4> List all answers",
            "<5> List all quizzes",
            "<6> Add new player",
            "<7> Add new admin",
            "<8> Add new question"
    ));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Register register = new Register();

        // SAMPLE_DATA
        List<User> players = new ArrayList<>(List.of(register.SAMPLE_PLAYERS));
        List<User> admins = new ArrayList<>(List.of(register.SAMPLE_ADMINS));
        List<Question> questions = new ArrayList<>(List.of(register.SAMPLE_QUESTIONS));
        List<Answer> answers = new ArrayList<>(List.of(register.SAMPLE_ANSWERS));
        List<Quiz> quizzes = new ArrayList<>(List.of(register.SAMPLE_QUIZZES));


        //SAMPLE_DATA
        printMenu();
        String input = scanner.nextLine();

        // Insert a valid command
        while (!isANumber(input) || !isInRange(input)) {
            System.out.println("Please inset a valid number in the range from 0 to " + (menu.size()-1));
            input = scanner.nextLine();
        }
        int command = Integer.parseInt(input);

        while (command != 0){
            switch (command){
                case 1:
                   players.stream().forEach(System.out::println);
                    break;
                case 2:
                    admins.stream().forEach(System.out::println);
                    break;
                case 3:
                    questions.stream().forEach(System.out::println);
                    break;
                case 4:
                    answers.stream().forEach(System.out::println);
                    break;
                case 5:
                    quizzes.stream().forEach(System.out::println);
                    break;
                case 6:
                    User playerToBeCreated = InputUtil.createNewPlayer(scanner);
                    if(playerToBeCreated != null) players.add(playerToBeCreated);
                    break;
                case 7:
                    User adminToBeCreated = InputUtil.createNewAdmin(scanner);
                    if(adminToBeCreated != null) admins.add(adminToBeCreated);
                    break;
                case 8:
                    Question questionToBeCreated = InputUtil.createNewQuestion(scanner);
                    if(questionToBeCreated != null) questions.add(questionToBeCreated);
                    break;
                case 9:
                    // Answer answerToBeCreated = InputUtil.createNewAnswer();
                    //if(answerToBeCreated != null) answers.add(answerToBeCreated);
                default:
                    System.out.println("Please inset a valid number in the range from 0 to " + (menu.size()-1));
            }
            printMenu();
            input = scanner.nextLine();
            //validate the input
            command = Integer.parseInt(input);

        }


    }
    public static void printMenu(){
        System.out.println();
        System.out.println(MAIN_MENU);
        System.out.println(delimeter);
        menu.stream().forEach(System.out::println);
        System.out.println();

    }
    static private boolean isANumber(String in){
        boolean aNumber = false;
        try {
            Integer.parseInt(in);
        }catch (NumberFormatException ex){
            return false;
        }
        return true;

    }
    static private boolean isInRange(String in){
        boolean valid = false;
        int num = Integer.parseInt(in);
        if(num>=0 && num < menu.size()){
            return true;
        }
        return false;
    }



}
