package quizz.views.loggedInMainMenu;

import java.util.ArrayList;
import java.util.List;

public class MainMenuBar {
    private static final String MAIN_MENU = "           M A I N    M E N U";
    private static final String delimeter = "=========================================";
    private static final List<String> menu = new ArrayList<>(List.of(
            "<0> EXIT from this program ",
            "<1> List all players ",
            "<2> List all admins",
            "<3> List all quizzes",
            "<4> List dashboard",
            "<5> Add new player",
            "<6> Add new admin",
            "<7> Add new quiz"
            //"<9> Update player",
            //"<10> Update quiz",
            //"<11> Delete player",
            //"<12> Delete quiz",
            //"<13> Update player"
            //"<14> Update quiz",

    ));
    public static void printMainMenu(){
        System.out.println();
        System.out.println(MAIN_MENU);
        System.out.println(delimeter);
        menu.stream().forEach(System.out::println);
        System.out.println(delimeter);
        System.out.println();

    }
}
