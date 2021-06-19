package academy.hiperQuiz.quizz.services;

import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

public interface UserService {
    Collection<User> getAllUsers();
    User getUserById(Long id) throws EntityNotFoundException;
    User getUserByUsername(String username) throws EntityNotFoundException;
    User addUser(User user) throws EntityAlreadyExistsException;
    List<User> addUsersBatch(List<User> users) throws EntityAlreadyExistsException;
    long dropAllUsers();
    User updateUser(User user) throws EntityNotFoundException;
    User deleteUserById(Long id) throws EntityNotFoundException;
    long getCount();
}
