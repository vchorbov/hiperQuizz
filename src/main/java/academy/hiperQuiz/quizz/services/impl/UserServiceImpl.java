package academy.hiperQuiz.quizz.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;

import academy.hiperQuiz.quizz.dao.UserRepository;
import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.services.UserService;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) throws EntityNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("User with ID='%d' not found", id)
        ));
    }

    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
        return userRepo.findUserByUsername(username).orElseThrow(() -> new EntityNotFoundException(
                String.format("User with username='%s' not found", username)
        ));
    }

    @Override
    public User addUser(User user) throws EntityAlreadyExistsException {
        user.setId(null);
        Date now = new Date();
        user.setCreated(now);
        user.setModified(now);
        return userRepo.create(user);
    }

    @Override
    public List<User> addUsersBatch(List<User> users) throws EntityAlreadyExistsException {
        // TODO: refactor the method addBatch in general
        int addedUsers = userRepo.createBatch(users);
        return users;
    }

    @Override
    public long dropAllUsers() {
        return userRepo.drop();
    }

    @Override
    public User updateUser(User user) throws EntityNotFoundException {
        return userRepo.update(user);
    }

    @Override
    public User deleteUserById(Long id) throws EntityNotFoundException {
        return userRepo.deleteById(id);
    }

    @Override
    public long getCount() {
        return userRepo.count();
    }
}
