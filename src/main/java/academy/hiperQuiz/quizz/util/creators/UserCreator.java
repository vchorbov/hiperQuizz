package academy.hiperQuiz.quizz.util.creators;

import academy.hiperQuiz.quizz.entity.*;

import academy.hiperQuiz.quizz.entity.*;
import java.util.Scanner;

public class UserCreator {
    public static User createNewPlayer(Scanner scanner){
        User playerToCreate = new Player();
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
        System.out.println("Email (cannot be empty): ");
        do {
            input = scanner.nextLine();

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

        //player`s picture
        System.out.println("Insert picture URL (optional -> empty line to skip): ");
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
              playerToCreate.setPicture(input);
        } while (playerToCreate.getPicture() == null);

        //player`s description
        System.out.println("Say something about yourself (optional -> empty line to skip): ");
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            if(input.length()>10 && input.length()<251){
                playerToCreate.setDescription(input);
            } else{
                System.err.println("For the description to be valid, it should be between 20 and 250 characters long. ");
            }
        } while (playerToCreate.getDescription() == null);

        return playerToCreate;


    }

    // logic for related to the creation of new admins
    public static User createNewAdmin(Scanner scanner){
        User adminToCreate = new Administrator();
        String input;

        // call createNewPlayer -> reusing the existing method
         adminToCreate = createNewPlayer(scanner);
         adminToCreate.setRole(Role.ADMIN);
         return adminToCreate;



    }

}
