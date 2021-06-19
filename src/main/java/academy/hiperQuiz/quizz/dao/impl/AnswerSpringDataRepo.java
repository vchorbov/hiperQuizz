package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerSpringDataRepo extends JpaRepository<Answer, Long> {
}
