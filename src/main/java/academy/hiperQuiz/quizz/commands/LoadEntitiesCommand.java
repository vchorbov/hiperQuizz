package academy.hiperQuiz.quizz.commands;

import academy.hiperQuiz.quizz.dao.QuizRepository;
import academy.hiperQuiz.quizz.dao.QuizResultRepository;
import academy.hiperQuiz.quizz.entity.AllCollections;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.dao.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


public class LoadEntitiesCommand implements Command {
    private UserRepository userRepo;
    private QuizRepository quizRepo;
    private QuizResultRepository quizResultRepo;
    private InputStream in;

    public LoadEntitiesCommand(InputStream in,
                               UserRepository userRepo,
                               QuizRepository quizRepo,
                               QuizResultRepository quizResultRepo
    ) {
        this.userRepo = userRepo;
        this.quizRepo = quizRepo;
        this.quizResultRepo = quizResultRepo;
        this.in = in;
    }

    @Override
    public String execute() {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            AllCollections allCollections = (AllCollections) ois.readObject();
            userRepo.createBatch(allCollections.getUsers());
            quizRepo.createBatch(allCollections.getQuizzes());
            quizResultRepo.createBatch(allCollections.getQuizResults());
            return "All collections loaded successfully";
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Error reading collections from file";
        } catch (EntityAlreadyExistsException e) {
            return "Error adding entities to repository";

        }
    }
}
