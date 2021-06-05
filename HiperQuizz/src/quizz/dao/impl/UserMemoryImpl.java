package quizz.dao.impl;

import quizz.dao.KeyGenerator;
import quizz.dao.UserRepository;
import quizz.model.User;

public class UserMemoryImpl extends RepositoryMemoryImpl<Long, User> implements UserRepository  {
    public UserMemoryImpl(){

    }

    public UserMemoryImpl(KeyGenerator<Long> keyGenerator){
        super(keyGenerator);
    }
}
