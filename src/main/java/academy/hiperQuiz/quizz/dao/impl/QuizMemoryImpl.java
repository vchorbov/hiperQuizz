package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.entity.Question;
import academy.hiperQuiz.quizz.dao.KeyGenerator;
import academy.hiperQuiz.quizz.dao.QuizRepository;
import academy.hiperQuiz.quizz.entity.Answer;
import academy.hiperQuiz.quizz.entity.Quiz;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class QuizMemoryImpl extends RepositoryMemoryImpl<Long, Quiz> implements QuizRepository{
    public QuizMemoryImpl() {
    }

    public QuizMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }

    @Override
    public Optional<List<Question>> findAllQuestions() {
        List<Question> questions = new ArrayList<>();
        findAll().stream().map(Quiz::getQuestions).map(questions::addAll);
        return Optional.of(questions);
    }

    @Override
    public Optional<List<Answer>> findAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        findAll().stream().filter(quiz -> quiz.getQuestions().addAll(questionList));
      //  questionList.stream().filter(question -> question.getAnswers().addAll(answers));
        return Optional.of(answers);
    }

}
