package quizz.model;

import java.util.List;
import java.util.Map;

public class Player<K,V> extends User {
    private List<QuizResult> results;
    private int overallScore;


    public Player() {
    }

    public Player(List<QuizResult> results) {
        this.results = results;
    }
    public Player(List<QuizResult> results, int overallScore) {
        this.results = results;
        this.overallScore = overallScore;

    }
    public Player(Long id, List<QuizResult> results) {
        super(id);
        this.results = results;
    }

    public Player(Long id, String username, String email, String password, Gender gender) {
        super(id, username, email, password, gender);
        this.results = results;
    }
    public Player (String username, String email, String password,
                  Gender gender, List<Quiz> quizzes, List<QuizResult> results) {
        super(username, email, password, gender, quizzes);
        this.results = results;
    }

    public Player(Long id, String username, String email, String password,
                  Gender gender, List<Quiz> quizzes, List<QuizResult> results) {
        super(id, username, email, password, gender, quizzes);
        this.results = results;
    }

    public Player(Long id, String username, String email, String password,
                  Gender gender, Role role, List<Quiz> quizzes, List<QuizResult> results) {
        super(id, username, email, password, gender, role, quizzes);
        this.results = results;
    }

    public Player(Long id, String username, String email, String password,
                  Gender gender, Role role, String picture, String description,
                  String metadata, boolean status, List<Quiz> quizzes, List<QuizResult> results) {
        super(id, username, email, password, gender, role, picture, description, metadata, status, quizzes);
        this.results = results;
    }

    public List<QuizResult> getResults() {
        return results;
    }

    public void setResults(List<QuizResult> results) {
        this.results = results;
    }

    public int getOverallScore() {
         return  results.stream()
                 .map(quizResult -> quizResult.getScore())
                 .reduce(0,(scoreSum, score) -> scoreSum + score);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("username='").append(getUsername()).append('\'');
        sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", gender=").append(getGender());
        sb.append(", role=").append(getRole());
        sb.append(", picture='").append(getPicture()).append('\'');
        sb.append(", description='").append(getDescription()).append('\'');
        sb.append(", metadata='").append(getMetadata()).append('\'');
        sb.append(", status=").append(getStatus());
        sb.append(", quizzes=").append(getQuizzes());
        sb.append("results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
