package academy.hiperQuiz.quizz.entity;

import java.io.Serializable;
import java.util.List;

public class AllCollections {
    private List<User> users;
    private List<Quiz> quizzes;
    private List<QuizResult> quizResults;

    public AllCollections() {
    }

    public AllCollections(List<User> users, List<Quiz> quizzes, List<QuizResult> quizResults) {
        this.users = users;
        this.quizzes = quizzes;
        this.quizResults = quizResults;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<QuizResult> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }
}
