package quizz.util;

import quizz.model.*;

import java.util.Scanner;

public class InputUtil {

    public static User createNewPlayer(Scanner scanner){
        User playerToCreate = new Player<>();
        String input;

        // player`s username
        System.out.println("Username (empty line for cancel): ");
        do{
            input = scanner.nextLine();
            if (input.isEmpty()) return null;
            boolean isValidUsername = input.length()>=2 && input.length()<=15 && input.matches("[A-Za-z]+");
            if(isValidUsername) {
                playerToCreate.setUsername(input);
            }else {
                System.err.println("For a username to be valid, it should be between 2 and 15 symbols long" +
                        " and consist of letters only.");
            }
            }while(playerToCreate.getUsername() == null);

        // player`s username
        System.out.println("Email (empty line to cancel): ");
        do {
            input = scanner.nextLine();
            if (input.isEmpty()) break;
            // alternative .+\@.+\..+
            if ( input.matches("^([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]" +
                    "+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\" +
                    "x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00" +
                    "-\\x7f])*\\x22))*\\x40([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\" +
                    "xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d)(\\x2e([^\\x00-\\x20\\x22\\x28\\" +
                    "x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[" +
                    "\\x00-\\x7f])*\\x5d))*$") )
               playerToCreate.setEmail(input);
            else
                System.err.println("Invalid email. Ex.: max.mustermann@gmail.com");
        } while (playerToCreate.getEmail() == null);

        //player`s password
        System.out.println("Insert a valid password (empty line to cancel): ");
        boolean valid = false;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;
            if ( input.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}") ) {
                playerToCreate.setPassword(input);
                valid = true;
            } else
                System.err.println("The password does not match the requirements: Minimum eight characters," +
                        " at least one letter, one number and one special character:");
        } while (!valid);

        //player`s gender
        System.out.println("Gender [M for male/ F for female , empty line for cancel]: ");
        boolean validSymbol = false;
        do {
            input = scanner.nextLine().trim().toUpperCase();
            if (input.isEmpty()) return null;
            if ( input.equals("M") ) {
                playerToCreate.setGender(Gender.MALE);
                validSymbol = true;
            } else if( input.equals("F") ) {
                playerToCreate.setGender(Gender.FEMALE);
                validSymbol = true;
            } else {
                System.err.println("Input does not match M or F.");
            }
        } while (!validSymbol);
        return playerToCreate;

    }

    public static User createNewAdmin(Scanner scanner){
        User adminToCreate = new Administrator();
        String input;

        // player`s username
        System.out.println("Username (empty line for cancel): ");
        do{
            input = scanner.nextLine();
            if (input.isEmpty()) return null;
            boolean isValidUsername = input.length()>=2 && input.length()<=15 && input.matches("[A-Za-z]+");
            if(isValidUsername) {
                adminToCreate.setUsername(input);
            }else {
                System.err.println("For a username to be valid, it should be between 2 and 15 symbols long" +
                        " and consist of letters only.");
            }
        }while(adminToCreate.getUsername() == null);

        // player`s username
        System.out.println("Email (empty line to cancel): ");
        do {
            input = scanner.nextLine();
            if (input.isEmpty()) break;
            // alternative .+\@.+\..+
            if ( input.matches("^([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]" +
                    "+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\" +
                    "x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00" +
                    "-\\x7f])*\\x22))*\\x40([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\" +
                    "xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d)(\\x2e([^\\x00-\\x20\\x22\\x28\\" +
                    "x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[" +
                    "\\x00-\\x7f])*\\x5d))*$") )
                adminToCreate.setEmail(input);
            else
                System.err.println("Invalid email. Ex.: max.mustermann@gmail.com");
        } while (adminToCreate.getEmail() == null);

        //player`s password
        System.out.println("Insert a valid password (empty line to cancel): ");
        boolean valid = false;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;
            if ( input.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}") ) {
                adminToCreate.setPassword(input);
                valid = true;
            } else
                System.err.println("The password does not match the requirements: Minimum eight characters," +
                        " at least one letter, one number and one special character:");
        } while (!valid);

        //player`s gender
        System.out.println("Gender [M for male/ F for female , empty line for cancel]: ");
        boolean validSymbol = false;
        do {
            input = scanner.nextLine().trim().toUpperCase();
            if (input.isEmpty()) return null;
            if ( input.equals("M") ) {
                adminToCreate.setGender(Gender.MALE);
                validSymbol = true;
            } else if( input.equals("F") ) {
                adminToCreate.setGender(Gender.FEMALE);
                validSymbol = true;
            } else {
                System.err.println("Input does not match M or F.");
            }
        } while (!validSymbol);
        return adminToCreate;
    }

    public static Question createNewQuestion(Scanner scanner) {
        Question questionToCreate = new Question();
        String input;

        // player`s username
        System.out.println("Question (empty line for cancel): ");
        do{
            input = scanner.nextLine();
            if (input.isEmpty()) return null;
            boolean isValidQuestion = input.length()>=10 && input.length()<=200;
            if(isValidQuestion) {
                questionToCreate.setText(input);
            }else {
                System.err.println("In order for the inserted question to be valid," +
                        " it should be of length between 10 and 200 characters.");
            }
        }while(questionToCreate.getText() == null);
        return questionToCreate;
    }


}