package quizz.controller;

import quizz.dao.impl.LongKeyGenerator;
import quizz.model.*;

import java.io.Serializable;
import java.util.List;

public class Register implements Serializable {
static LongKeyGenerator lkg = new LongKeyGenerator();

    // Sample Questions
    static Question lambdaQuestion = new Question("When were Lambda functions introduced in Java?");
    static Question javaQuestion = new Question("Which of the statements is true for Java?");
    static Question OOPQuestion = new Question("What does DI mean?");

    //Sample Users
    static User sUser = new Player(lkg.getNextId(),"sample1","sample1@abv.bg", "sdds", Gender.MALE);



    public static final User[] SAMPLE_PLAYERS = {
            new Player(lkg.getNextId(),"spy", "spy@gmail.com", "????", Gender.MALE),
            new Player(lkg.getNextId(),"zina", "zina1@gmail.com", "????", Gender.FEMALE),
            new Player(lkg.getNextId(),"lora", "lori4ka@abv.bg", ".....", Gender.FEMALE),
    };

    public static final Administrator[] SAMPLE_ADMINS = {
            new Administrator(lkg.getNextId(),"adam", "adam.a@gmail.com", "xxxx", Gender.MALE,Role.ADMIN)
    };

    public static final Question[] SAMPLE_QUESTIONS = {
            OOPQuestion, javaQuestion, lambdaQuestion
    };

    public static final Answer[] SAMPLE_ANSWERS = {
            new Answer(OOPQuestion, "Deep Information", 3),
            new Answer(OOPQuestion, "Dark Interface", 3),
            new Answer(OOPQuestion, "Dependency Injection", 3),
            new Answer(lambdaQuestion, "1.5", 2),
            new Answer(lambdaQuestion, "1.3", 2),
            new Answer(lambdaQuestion, "1.8", 2),
            new Answer(javaQuestion, "Object Oriented", 5),
            new Answer(javaQuestion, "Functional", 5),
            new Answer(javaQuestion, "Not C-like", 5),

    };

    public static final Quiz[] SAMPLE_QUIZZES = {
      new Quiz("Java Quizz", sUser,
              "This quizz will help you to test your Java knowledge.",
              List.of(SAMPLE_QUESTIONS), 20)
    };


}