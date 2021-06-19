package academy.hiperQuiz.quizz.views.noUserLoggedInMenu;

import java.util.ArrayList;
import java.util.List;

public class LoginMenuBar {
    private static final String LOGIN_MENU = "           L O G I N  M E N U";
    private static final String delimeter = "=========================================";
    private static final List<String> menu = new ArrayList<>(List.of(
            "<0> EXIT from this program ",
            "<1> Login",
            "<2> Sign up"
    ));

    public static void printLoginMenu(){
        System.out.println();
        System.out.println(LOGIN_MENU);
        System.out.println(delimeter);
        menu.stream().forEach(System.out::println);
        System.out.println(delimeter);
        System.out.println();

    }
}
