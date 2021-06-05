package quizz.util.creators;

import quizz.model.Answer;
import quizz.model.Question;
import quizz.model.Quiz;

import java.util.List;
import java.util.Scanner;

public class AnswerCreator {
    //does not return the answer but rather sets it to the question passed as argument
public static void createNewAnswer(Scanner scanner, Question question) {
        Answer answerToCreate = new Answer();
        String input;

    if(question == null){
        System.err.println("For a answer to be created successfully, it should have a question to be assigned to." +
                "The question cannot be equal to null");
        return;
    }else{
       // answerToCreate.setQuestion(question);
    }

    // answers` description
    System.out.println("Answer text (2 to 50 chars): ");
    do {
        input = scanner.nextLine();
        boolean isValidText = input.length() >= 2 && input.length() <= 50;
        if (isValidText) {
            answerToCreate.setText(input);
        } else {
            System.err.println("For a answer text to be valid, it should be between 2 and 50 symbols long.");
        }
    } while (answerToCreate.getText() == null);

    //answer`s picture
    System.out.println("Insert a picture for the answer (empty line to cancel): ");
    boolean valid = true;
    do {
        input = scanner.nextLine().trim();
        if (input.isEmpty()) break;
        answerToCreate.setPicture(input);
            valid = true;
    } while (!valid);

    //answer`s score(can be negative)
    System.out.println("Add score to the answer (can be negative): ");
    int score;
    do {
        input = scanner.nextLine().trim();
        try{
            score = Integer.parseInt(input);
            answerToCreate.setScore(score);
        }catch (NumberFormatException ex){
            System.err.println("Insert a valid natural(or negative) number score for the answer. " +
                    "DIFFERENT from 0");
        }
    } while (answerToCreate.getScore() == 0);

    question.setAnswers(List.of(answerToCreate));

    }
}
