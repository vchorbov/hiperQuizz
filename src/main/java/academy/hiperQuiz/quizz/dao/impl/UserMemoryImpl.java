package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.dao.KeyGenerator;
import academy.hiperQuiz.quizz.dao.UserRepository;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;
import academy.hiperQuiz.quizz.entity.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserMemoryImpl extends RepositoryMemoryImpl<Long, User> implements UserRepository  {
    private Map<Long, User> entities = new ConcurrentHashMap<>();
    private KeyGenerator<Long> keyGenerator;

    public UserMemoryImpl(){
    }

    public UserMemoryImpl(KeyGenerator<Long> keyGenerator){
        super(keyGenerator);
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws EntityNotFoundException {
        return super.findAll()
                .stream()
                .filter(entity -> entity.getUsername().equals(username))
                .findFirst();
    }
}
