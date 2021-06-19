package academy.hiperQuiz.quizz.services;

import academy.hiperQuiz.quizz.entity.Quiz;
import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.List;

public interface QuizService {
    Collection<Quiz> getAllQuizzes();
    Quiz getQuizById(Long id) throws EntityNotFoundException;
    Quiz addQuiz(Quiz quiz) throws EntityAlreadyExistsException;
    List<Quiz> addQuizzesBatch(List<Quiz> quizzes) throws EntityAlreadyExistsException;
    long dropAllQuizzes();
    Quiz updateQuiz(Quiz quiz) throws EntityNotFoundException;
    Quiz deleteQuizById(Long id) throws EntityNotFoundException;
    long getCount();
}
