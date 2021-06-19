package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.entity.Quiz;
import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface QuizzesSpringDataRepo extends JpaRepository<Quiz, Long> {


}
