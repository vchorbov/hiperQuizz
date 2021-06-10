package quizz.util.printers;

import quizz.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static quizz.util.Alignment.LEFT;
import static quizz.util.Alignment.RIGHT;

public class DashboardPrinter {
    private static List<PrintUtil.ColumnDescriptor> dashboardColumns = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
            new PrintUtil.ColumnDescriptor("username", "Username", 9, LEFT),
            new PrintUtil.ColumnDescriptor("role", "Role", 12, LEFT),
            new PrintUtil.ColumnDescriptor("rank", "Rank", 12, LEFT),
            new PrintUtil.ColumnDescriptor("overallScore", "Overall Score", 12, LEFT),
            new PrintUtil.ColumnDescriptor("description", "Description", 19, LEFT),
            new PrintUtil.ColumnDescriptor("quizzes", "Quizzes", 29, LEFT)
    ));

    public static void printTableForUsers(UserRepository repository){
        UserRepository userRepo = repository;
        String usersReport = PrintUtil.formatTable(dashboardColumns, userRepo.findAll(), "Dashboard List:");
        System.out.println(usersReport);
    }
}
