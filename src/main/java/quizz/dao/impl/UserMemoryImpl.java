package quizz.dao.impl;

import quizz.dao.KeyGenerator;
import quizz.dao.UserRepository;
import quizz.exception.EntityNotFoundException;
import quizz.model.User;

import java.util.Map;
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
    public User findUserByUsername(String username) throws EntityNotFoundException {
        return super.findAll()
                .stream()
                .filter(entity -> entity.getUsername().equals(username))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }
}
