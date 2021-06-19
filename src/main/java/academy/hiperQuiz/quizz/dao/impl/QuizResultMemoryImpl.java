package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.dao.KeyGenerator;
import academy.hiperQuiz.quizz.dao.QuizResultRepository;
import academy.hiperQuiz.quizz.entity.QuizResult;

public class QuizResultMemoryImpl extends RepositoryMemoryImpl<Long, QuizResult> implements QuizResultRepository {

    public QuizResultMemoryImpl() {
    }

    public QuizResultMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
