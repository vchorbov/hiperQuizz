package quizz.views.noUserLoggedInMenu;

import quizz.dao.UserRepository;
import quizz.exception.EntityNotFoundException;
import quizz.model.User;

import java.util.Optional;
import java.util.Scanner;

public class NoUserView {

    public static User exposeMainMenu(Scanner scanner, UserRepository userRepository) {
        LoginMenuBar.printLoginMenu();
        String input = scanner.nextLine();
        User userForThisSession = new User();
        int command = Integer.parseInt(input);
        Optional<User> placeholder;
            switch (command) {
                case 0: return null;
                case 1: //login with existing account
                    do{
                        System.out.println("Username:");
                        input = scanner.nextLine();
                        try {
                            placeholder = userRepository.findUserByUsername(input);
                        } catch (EntityNotFoundException e) {
                            e.printStackTrace();
                        }



                    }while (userForThisSession == null);
                    break;
                case 2: //create new account

                    break;
            }



        return null;
    }
}