package academy.hiperQuiz.quizz.util.creators;

import academy.hiperQuiz.quizz.entity.Quiz;
import academy.hiperQuiz.quizz.entity.User;

import java.util.Scanner;

public class QuizCreator {
    public static Quiz createNewQuiz(Scanner scanner, User creator) {
        Quiz quizToCreate = new Quiz();
        String input;

        // set the creator of the quiz
        if (creator == null) {
            System.err.println("In order to have the permission to create a new quiz," +
                    " you should be logged in with an existing account.");
            return null;
        } else {
            quizToCreate.setAuthor(creator);
        }

        // quiz` title
        System.out.println("Title of the quiz (empty line for cancel): ");
        do {
            input = scanner.nextLine();
            if (input.isEmpty()) return null;
            boolean isValidTitle = input.length() >= 2 && input.length() <= 80;
            if (isValidTitle) {
                quizToCreate.setTitle(input);
            } else {
                System.err.println("For a title to be valid, it should be between 2 and 80 symbols long.");
            }
        } while (quizToCreate.getTitle() == null);

        // quiz` description
        System.out.println("Description of the quiz (10 to 250 chars): ");
        do {
            input = scanner.nextLine();
            boolean isValidDescription = input.length() >= 10 && input.length() <= 250;
            if (isValidDescription) {
                quizToCreate.setDescription(input);
            } else {
                System.err.println("For a description to be valid, it should be between 10 and 250 symbols long.");
            }
        } while (quizToCreate.getDescription() == null);

        // quiz` questions
        System.out.println("Question(s) of the quiz: (at least one): ");
        do {

            QuestionCreator.createNewQuestion(scanner, quizToCreate);

        } while (quizToCreate.getQuestions().size() <= 0);

        // quiz` expected duration
        System.out.println("The expected duration of the whole quiz (optional - can be left blank)");
        do {
            input = scanner.nextLine();
            if(input.isBlank()) break;
            try {
                int exDur = Integer.parseInt(input);
                quizToCreate.setExpectedDuration(exDur);
            }catch (NumberFormatException ex){
                System.err.println("Insert a valid natural number for the expected duration in minutes.");
                ex.printStackTrace();
            }

        } while (quizToCreate.getExpectedDuration() == 0);

        // quiz` tags
        System.out.println("Tags related to the quiz, separated with commas Ex: java, OOP, programming");
        do {
            input = scanner.nextLine();
            boolean isValidDescription = input.length() >= 2 && input.contains(",");
            if (isValidDescription) {
                quizToCreate.setTags(input);
            } else {
                System.err.println("For a tag to be valid, it should contain csv values");
            }
        } while (quizToCreate.getTags() == null);


        return quizToCreate;
    }
}
