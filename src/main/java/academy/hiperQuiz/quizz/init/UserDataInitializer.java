package academy.hiperQuiz.quizz.init;

import academy.hiperQuiz.quizz.dao.impl.AnswerSpringDataRepo;
import academy.hiperQuiz.quizz.dao.impl.QuestionSpringDataRepo;
import academy.hiperQuiz.quizz.dao.impl.QuizzesSpringDataRepo;
import academy.hiperQuiz.quizz.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import academy.hiperQuiz.quizz.dao.UserRepository;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;

import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class UserDataInitializer implements CommandLineRunner {

    //Sample Users
     User sUser = new Player("sample1","sample1@abv.bg", "asd123ASD!!", Gender.MALE);
    //Sample quiz
     Quiz quiz1 = new Quiz("Java Quizz",sUser,
            "This Quiz will help you to test your Java knowledge.",
            20);
    // Sample Questions
     Question lambdaQuestion = new Question(quiz1,"When were Lambda functions introduced in Java?");
     Question javaQuestion = new Question(quiz1,"Which of the statements is true for Java?");
     Question OOPQuestion = new Question(quiz1,"What does DI mean?");

    List<User> SAMPLE_USERS = List.of(
            new Player("spy", "spy@gmail.com", "asd123ASD!!", Gender.MALE),

            new Player("zina", "zina1@gmail.com", "asd123ASD!!", Gender.FEMALE),

            new Player("lora", "lori4ka@abv.bg", "asd123ASD!!", Gender.FEMALE),

            new Administrator("adam", "adam.a@gmail.com", "xxxxxxxx", Gender.MALE,Role.ADMIN)
    );
      List<Question> SAMPLE_QUESTIONS = List.of(
            OOPQuestion, javaQuestion, lambdaQuestion);

      Collection<Answer> SAMPLE_ANSWERS = List.of(
            new Answer(OOPQuestion, "Deep Information", 3),
            new Answer(OOPQuestion, "Dark Interface", 3),
            new Answer(OOPQuestion, "Dependency Injection", 3),
            new Answer(lambdaQuestion, "Version 1.5", 2),
            new Answer(lambdaQuestion, "Version 1.3", 2),
            new Answer(lambdaQuestion, "Version 1.8", 2),
            new Answer(javaQuestion, "Object Oriented", 5),
            new Answer(javaQuestion, "Functional", 5),
            new Answer(javaQuestion, "Not C-like", 5));

    public final List<Quiz> SAMPLE_QUIZZES = List.of(quiz1);

    @Autowired
    UserRepository userRepo;
    @Autowired
    QuizzesSpringDataRepo quizRepo;
    @Autowired
    AnswerSpringDataRepo answerRepo;
    @Autowired
    QuestionSpringDataRepo questionRepo;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() == 0) {
            log.info("Initializing DB with sample users.");
            try {
                answerRepo.saveAll(SAMPLE_ANSWERS);
                questionRepo.saveAll(SAMPLE_QUESTIONS);
                quizRepo.saveAll(SAMPLE_QUIZZES);
                userRepo.createBatch(SAMPLE_USERS);
            } catch (EntityAlreadyExistsException e) {
                log.error("Error initializing users", e);
            }
        }
    }
}
