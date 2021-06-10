package quizz.dao.impl;

import quizz.dao.KeyGenerator;
import quizz.dao.QuizResultRepository;
import quizz.model.QuizResult;

public class QuizResultMemoryImpl extends RepositoryMemoryImpl<Long, QuizResult> implements QuizResultRepository {

    public QuizResultMemoryImpl() {
    }

    public QuizResultMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
