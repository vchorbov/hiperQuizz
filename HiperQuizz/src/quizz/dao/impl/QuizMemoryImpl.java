package quizz.dao.impl;

import quizz.dao.QuizRepository;
import quizz.model.Answer;
import quizz.model.Question;
import quizz.model.Quiz;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class QuizMemoryImpl extends RepositoryMemoryImpl<Long, Quiz> implements QuizRepository{
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
        questionList.stream().filter(question -> question.getAnswers().addAll(answers));
        return Optional.of(answers);
    }

}
