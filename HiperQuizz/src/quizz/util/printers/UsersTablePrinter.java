package quizz.util.printers;

import quizz.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static quizz.util.Alignment.*;

public class UsersTablePrinter {

    private static List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
            new PrintUtil.ColumnDescriptor("username", "Username", 9, LEFT),
            new PrintUtil.ColumnDescriptor("picture", "Picture", 12, LEFT),
            new PrintUtil.ColumnDescriptor("email", "email", 12, LEFT),
            new PrintUtil.ColumnDescriptor("gender", "Gender", 12, LEFT),
            new PrintUtil.ColumnDescriptor("role", "Role", 12, LEFT),
            new PrintUtil.ColumnDescriptor("description", "Description", 19, LEFT),
            new PrintUtil.ColumnDescriptor("quizzes", "Quizzes", 29, LEFT),
            new PrintUtil.ColumnDescriptor("created", "Created", 19, CENTER),
            new PrintUtil.ColumnDescriptor("updated", "Updated", 19, CENTER)
    ));

    public static void printTableForUsers(UserRepository repository){
        UserRepository userRepo = repository;
        String usersReport = PrintUtil.formatTable(userColumns, userRepo.findAll(), "Products List:");
        System.out.println(usersReport);
    }

}
