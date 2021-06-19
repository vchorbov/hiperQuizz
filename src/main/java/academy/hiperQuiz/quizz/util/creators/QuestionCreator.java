package academy.hiperQuiz.quizz.util.creators;

import academy.hiperQuiz.quizz.entity.Question;
import academy.hiperQuiz.quizz.entity.Quiz;
import java.util.List;
import java.util.Scanner;

public class QuestionCreator {
    //does not return the question but rather sets them to the quiz passed as argument
    public static void createNewQuestion(Scanner scanner, Quiz quiz) {
        Question questionToCreate = new Question();
        String input;

        if(quiz == null){
            System.err.println("For a question to be created successfully, it should have a quiz to be assigned to." +
                    "The quiz cannot be equal to null");
            return;
        }else{
         //   questionToCreate.setQuiz(quiz);
        }

        // quiz` description
        System.out.println("Question text (10 to 250 chars): ");
        do {
            input = scanner.nextLine();
            boolean isValidText = input.length() >= 10 && input.length() <= 250;
            if (isValidText) {
               questionToCreate.setText(input);
            } else {
                System.err.println("For a question text to be valid, it should be between 10 and 250 symbols long.");
            }
        } while (questionToCreate.getText() == null);

        // quiz` answer
        System.out.println("Answer to the created question (10 to 250 chars): ");
        do {

            AnswerCreator.createNewAnswer(scanner, questionToCreate);

        } while (questionToCreate.getAnswers().size() == 0);

        quiz.setQuestions(List.of(questionToCreate));

    }
}
