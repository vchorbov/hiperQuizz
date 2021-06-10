package quizz.dao;

import quizz.model.Answer;
import quizz.model.Question;
import quizz.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends Repository<Long, Quiz>{
    // Q&A
    Optional<List<Question>> findAllQuestions();
    Optional<List<Answer>> findAllAnswers();
}
