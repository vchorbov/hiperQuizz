package quizz.dao;

import quizz.exception.EntityNotFoundException;
import quizz.model.User;

//CRUD
public interface UserRepository extends Repository<Long, User> {

    User findUserByUsername(String username) throws EntityNotFoundException;
}
