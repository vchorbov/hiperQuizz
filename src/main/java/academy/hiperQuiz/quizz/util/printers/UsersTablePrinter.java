package academy.hiperQuiz.quizz.util.printers;

import academy.hiperQuiz.quizz.dao.UserRepository;
import academy.hiperQuiz.quizz.util.Alignment;

import java.util.ArrayList;
import java.util.List;

public class UsersTablePrinter {

    private static List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, Alignment.RIGHT),
            new PrintUtil.ColumnDescriptor("username", "Username", 9, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("picture", "Picture", 12, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("email", "email", 12, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("gender", "Gender", 12, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("role", "Role", 12, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("description", "Description", 19, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("quizzes", "Quizzes", 29, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("created", "Created", 19, Alignment.CENTER),
            new PrintUtil.ColumnDescriptor("updated", "Updated", 19, Alignment.CENTER)
    ));

    public static void printTableForUsers(UserRepository repository){
        UserRepository userRepo = repository;
        String usersReport = PrintUtil.formatTable(userColumns, userRepo.findAll(), "Users List:");
        System.out.println(usersReport);
    }

}
