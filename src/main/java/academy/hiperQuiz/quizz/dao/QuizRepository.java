package academy.hiperQuiz.quizz.dao;

import academy.hiperQuiz.quizz.entity.Answer;
import academy.hiperQuiz.quizz.entity.Question;
import academy.hiperQuiz.quizz.entity.Quiz;
import java.util.List;
import java.util.Optional;

public interface QuizRepository extends Repository<Long, Quiz>{
    // Q&A
    Optional<List<Question>> findAllQuestions();
    Optional<List<Answer>> findAllAnswers();
}
