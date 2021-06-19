package academy.hiperQuiz.quizz.dao;

import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;

import java.util.Optional;

//CRUD
public interface UserRepository extends Repository<Long, User> {

    Optional<User> findUserByUsername(String username) throws EntityNotFoundException;
}
