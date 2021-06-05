package quizz.persistence;

import quizz.controller.Register;
import quizz.dao.UserRepository;
import quizz.dao.impl.UserMemoryImpl;
import quizz.exception.EntityAlreadyExistsException;
import quizz.model.Gender;
import quizz.model.Player;
import quizz.model.User;

import java.util.Arrays;

public class UserDataLoader {
    UserRepository  playerRepo = new UserMemoryImpl();

    public static final User[] SAMPLE_PLAYERS = {
            new Player("spy", "spy@gmail.com", "????", Gender.MALE),
            new Player("zina", "zina1@gmail.com", "????", Gender.FEMALE),
            new Player("lora", "lori4ka@abv.bg", ".....", Gender.FEMALE),
    };

    /*
 Arrays.stream(SAMPLE_PLAYERS).sequential().forEach(player -> {
        try {
            playerRepo.create(player);
        } catch (EntityAlreadyExistsException e) {
            e.printStackTrace();
        }
    });
*/


}
