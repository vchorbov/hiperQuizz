package academy.hiperQuiz.quizz.web;

import academy.hiperQuiz.quizz.entity.Quiz;
import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.services.QuizService;
import academy.hiperQuiz.quizz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(path = "/api/quizzes", produces = APPLICATION_JSON_VALUE)
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public Collection<Quiz> getQuizzes() {
        return quizService.getAllQuizzes();
    }
}
