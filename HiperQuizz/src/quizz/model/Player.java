package quizz.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Player<K,V> extends User implements Serializable {
    private List<QuizResult> results;
    private int overallScore = 0;
    private Rank rank;


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
    public Player( String username, String email, String password, Gender gender) {
        super(username, email, password, gender);
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

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public int getOverallScore() {
        return this.overallScore;

    }

    public Rank getRank() {
      if(overallScore<=100){
          rank = Rank.BRONZE;
      }else if(overallScore>100 && overallScore<=500){
          rank = Rank.SILVER;
      }else if(overallScore>500 && overallScore<=1000){
          rank = Rank.GOLD;
      }else if(overallScore>1000){
          rank = Rank.PLATINUM;
      }
      return rank;
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
