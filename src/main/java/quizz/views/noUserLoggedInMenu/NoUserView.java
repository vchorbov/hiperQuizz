package quizz.views.noUserLoggedInMenu;

import quizz.dao.UserRepository;
import quizz.exception.EntityNotFoundException;
import quizz.model.Player;
import quizz.model.User;

import java.util.Scanner;

public class NoUserView {

    public static User exposeLoginMenu(Scanner scanner, UserRepository userRepository) {
        LoginMenuBar.printLoginMenu();
        String input = scanner.nextLine();
        User userForThisSession = new User();
        int command = Integer.parseInt(input);
        User placeholder = new Player();
        switch (command) {
            case 0:
                return null;
            case 1: //login with existing account
                while (command != 0 ) {
                    System.out.println("Username: ");
                    String usernameIn = scanner.nextLine();
                    System.out.println("Password: ");
                    String passwordIn = scanner.nextLine();

                    try {
                        placeholder = userRepository.findUserByUsername(usernameIn.trim());
                        if (placeholder != null) {
                            if (placeholder.getPassword().equals(passwordIn)) {
                                System.out.println(String.format(
                                        "Successfully logged in. Hello %s! Enjoy the quizzes!"
                                        , usernameIn));
                                return placeholder;
                            }else{
                                System.out.println("Wrong username or password.");
                                System.out.println("To exit press 0, to try again 1");
                                command = Integer.parseInt(scanner.nextLine());
                            }
                        }
                    } catch (EntityNotFoundException e) {
                        System.out.println(String.format("A user with username: %s does not exist", usernameIn));
                        System.out.println("To exit press 0, to try again 1");
                        command = Integer.parseInt(scanner.nextLine());

                    }

                }
               return null;
            case 2: //create new account(Register)

                // TODO: Implement a logic for the registration of new user -> reuse the existing methods from package quizz.util.creators;
                break;
        }


        return null;
    }
}