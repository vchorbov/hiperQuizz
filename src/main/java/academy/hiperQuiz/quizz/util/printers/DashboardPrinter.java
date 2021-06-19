package academy.hiperQuiz.quizz.util.printers;

import academy.hiperQuiz.quizz.util.Alignment;
import academy.hiperQuiz.quizz.dao.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class DashboardPrinter {
    private static List<PrintUtil.ColumnDescriptor> dashboardColumns = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, Alignment.RIGHT),
            new PrintUtil.ColumnDescriptor("username", "Username", 9, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("role", "Role", 12, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("rank", "Rank", 12, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("overallScore", "Overall Score", 12, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("description", "Description", 19, Alignment.LEFT),
            new PrintUtil.ColumnDescriptor("quizzes", "Quizzes", 29, Alignment.LEFT)
    ));

    public static void printTableForUsers(UserRepository repository){
        UserRepository userRepo = repository;
        String usersReport = PrintUtil.formatTable(dashboardColumns, userRepo.findAll(), "Dashboard List:");
        System.out.println(usersReport);
    }
}
