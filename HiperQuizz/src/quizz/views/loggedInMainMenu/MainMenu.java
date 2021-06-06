package quizz.views.loggedInMainMenu;

import quizz.controller.Register;

import java.io.InputStream;
import java.util.*;

public class MainMenu {
    private static final List<MenuItem> menuItems = new ArrayList<>();
    private static final Map<MenuCommand, Command> commands = new EnumMap<MenuCommand, Command>(MenuCommand.class);
    private static Register register = new Register();
    private final Scanner in;

    public MainMenu(InputStream inStream) {
        this.in = new Scanner(inStream);
    }
}
