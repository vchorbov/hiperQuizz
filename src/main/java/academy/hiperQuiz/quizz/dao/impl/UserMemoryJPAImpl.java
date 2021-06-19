package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.dao.UserRepository;
import academy.hiperQuiz.quizz.entity.Gender;
import academy.hiperQuiz.quizz.entity.Player;
import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.*;

import academy.hiperQuiz.quizz.exception.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@Slf4j
@Repository
@Transactional
public class UserMemoryJPAImpl implements UserRepository {
    List<User> SAMPLE_USERS = List.of(
            new Player("spy", "spy@gmail.com", "????", Gender.MALE),
            new Player("zina", "zina1@gmail.com", "????", Gender.FEMALE),
            new Player("lora", "lori4ka@abv.bg", ".....", Gender.FEMALE)
    );

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TransactionTemplate template;

    @Autowired
    ModelMapper modelMapper;

    public UserMemoryJPAImpl() {
    }



    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
       return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws academy.hiperQuiz.quizz.exception.EntityNotFoundException {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = '" + username + "'", User.class);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public User create(User entity) throws EntityAlreadyExistsException {
        em.persist(entity);
        return entity;
    }

    @Override
    public int createBatch(Collection<User> entities) throws EntityAlreadyExistsException {
        List<User> results = new ArrayList<>();
            for (User u : entities) {
                em.persist(u);
                results.add(u);
            }
            em.close();
        return results.size();
    }

    @Override
    public User update(User entity) throws academy.hiperQuiz.quizz.exception.EntityNotFoundException {
        Optional<User> old = findById(entity.getId());
        if(old.isEmpty()){
            throw new academy.hiperQuiz.quizz.exception.EntityNotFoundException(String.format("Entity with ID='%s' does not exist.", entity.getId()));
        }
          User result = em.merge(entity);
          return result;
    }

    @Override
    public User deleteById(Long id) throws academy.hiperQuiz.quizz.exception.EntityNotFoundException {
        User userToBeDeleted = em.find(User.class, id); //findById(id).orElse(null);
        if(userToBeDeleted == null){
            throw new EntityNotFoundException("User with ID = '" + id + "' not found");
        }
        em.remove(userToBeDeleted);
        return userToBeDeleted;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return (Long) em.createQuery("SELECT COUNT(u) FROM User u").getSingleResult();
    }

    @Override
    public long drop() {
        long count = em.createQuery("DELETE FROM User u").executeUpdate();
        log.info("Dropping {} users.", count);
        return count;
    }


    @Transactional(propagation = NOT_SUPPORTED)
    public Statistics getStatistics(){
        return em.unwrap(Session.class).getSessionFactory().getStatistics();
    }
}
