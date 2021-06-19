package academy.hiperQuiz.quizz.commands;

import academy.hiperQuiz.quizz.dao.QuizRepository;
import academy.hiperQuiz.quizz.dao.QuizResultRepository;
import academy.hiperQuiz.quizz.entity.AllCollections;
import academy.hiperQuiz.quizz.dao.UserRepository;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class SaveEntitiesCommand implements Command {
    private UserRepository userRepo;
    private QuizRepository quizRepo;
    private QuizResultRepository quizResultRepo;
    private OutputStream out;

    public SaveEntitiesCommand(OutputStream out,
                               UserRepository userRepo,
                               QuizRepository quizRepo,
                               QuizResultRepository quizResultRepo
                               ) {
        this.userRepo = userRepo;
        this.quizRepo = quizRepo;
        this.quizResultRepo = quizResultRepo;
        this.out = out;
    }
    @Override
    public String execute() {
            AllCollections allCollections = new AllCollections(
                    userRepo.findAll(),
                    quizRepo.findAll(),
                    quizResultRepo.findAll()
            );
        try(ObjectOutputStream oos = new ObjectOutputStream(out)){
            oos.writeObject(allCollections);
            return "All collections saved successfully";
        } catch (IOException e) {
            return "Error writing collections to file";
        }
    }
}
