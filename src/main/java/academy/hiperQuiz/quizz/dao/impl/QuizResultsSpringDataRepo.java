package academy.hiperQuiz.quizz.dao.impl;

import academy.hiperQuiz.quizz.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultsSpringDataRepo extends JpaRepository<QuizResult, Long> {

}
