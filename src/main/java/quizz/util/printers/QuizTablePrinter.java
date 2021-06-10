package quizz.util.printers;

import quizz.dao.QuizRepository;

import java.util.ArrayList;
import java.util.List;

import static quizz.util.Alignment.*;

public class QuizTablePrinter {

    private static List<PrintUtil.ColumnDescriptor> quizColumns = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 3, RIGHT),
            new PrintUtil.ColumnDescriptor("title", "Title", 7, LEFT),
            new PrintUtil.ColumnDescriptor("author", "Author", 50, LEFT),
            new PrintUtil.ColumnDescriptor("description", "Description", 50, LEFT),
            new PrintUtil.ColumnDescriptor("expectedDuration", "Expected Duration", 20, LEFT),
            new PrintUtil.ColumnDescriptor("tags", "Tags", 30, CENTER),
            new PrintUtil.ColumnDescriptor("created", "Ctreated", 19, CENTER),
            new PrintUtil.ColumnDescriptor("updated", "Updated", 19, CENTER)
    ));

    public static void printTableForUsers(QuizRepository repository){
        QuizRepository quizRepository = repository;
        String quizReport = PrintUtil.formatTable(quizColumns, quizRepository.findAll(), "List of all quizzes:");
        System.out.println(quizReport);
    }
}

