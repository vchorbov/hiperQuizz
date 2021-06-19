package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSpringDataRepo extends JpaRepository<Question, Long> {
}
