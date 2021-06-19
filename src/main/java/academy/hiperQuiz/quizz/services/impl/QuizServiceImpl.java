package academy.hiperQuiz.quizz.services.impl;

import academy.hiperQuiz.quizz.dao.QuizRepository;
import academy.hiperQuiz.quizz.dao.impl.QuizzesSpringDataRepo;
import academy.hiperQuiz.quizz.entity.Quiz;
import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;
import academy.hiperQuiz.quizz.services.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    final QuizzesSpringDataRepo quizRepo;
    final ModelMapper modelMapper;

    @Autowired
    public QuizServiceImpl(QuizzesSpringDataRepo quizRepo, ModelMapper modelMapper) {
        this.quizRepo = quizRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public Collection<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) throws EntityNotFoundException {
        return quizRepo.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }

    @Override
    public Quiz addQuiz(Quiz quiz) throws EntityAlreadyExistsException {
        return quizRepo.save(quiz);
    }

    @Override
    public List<Quiz> addQuizzesBatch(List<Quiz> quizzes) throws EntityAlreadyExistsException {
        return quizRepo.saveAll(quizzes);
    }

    @Override
    public long dropAllQuizzes() {
        long size = quizRepo.count();
        quizRepo.deleteAll();
        return size;
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) throws EntityNotFoundException {
        long id = quiz.getId();
        Quiz toUpdate = quizRepo.getById(id);
        quizRepo.deleteById(id);
        return  quizRepo.save(quiz);
    }

    @Override
    public Quiz deleteQuizById(Long id) throws EntityNotFoundException {
        Quiz toBeDeleted = quizRepo.getById(id);
        quizRepo.deleteById(id);
        return toBeDeleted;
    }

    @Override
    public long getCount() {
        return quizRepo.count();
    }
}
