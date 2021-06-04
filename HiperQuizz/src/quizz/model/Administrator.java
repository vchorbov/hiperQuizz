package quizz.model;

import java.util.List;

public class Administrator extends User {
    List<Quiz> quizzesBlocked;

    public Administrator() {
    }

    public Administrator(List<Quiz> quizzesBlocked) {
        this.quizzesBlocked = quizzesBlocked;
    }

    public Administrator(Long id, List<Quiz> quizzesBlocked) {
        super(id);
        this.quizzesBlocked = quizzesBlocked;
    }

    public Administrator(Long id, String username, String email, String password,
                         Gender gender) {
        super(id, username, email, password, gender);
    }

    public Administrator(Long id, String username, String email, String password,
                         Gender gender, List<Quiz> quizzes, List<Quiz> quizzesBlocked) {
        super(id, username, email, password, gender, quizzes);
        this.quizzesBlocked = quizzesBlocked;
    }

    public Administrator(Long id, String username, String email, String password,
                         Gender gender, Role role, List<Quiz> quizzes, List<Quiz> quizzesBlocked) {
        super(id, username, email, password, gender, role, quizzes);
        this.quizzesBlocked = quizzesBlocked;
    }

    public Administrator(Long id, String username, String email, String password, Gender gender, Role role, String picture,
                         String description, String metadata, boolean status, List<Quiz> quizzes, List<Quiz> quizzesBlocked) {
        super(id, username, email, password, gender, role, picture, description, metadata, status, quizzes);
        this.quizzesBlocked = quizzesBlocked;
    }

    public List<Quiz>  getQuizzesBlocked() {
        return quizzesBlocked;
    }

    public void setQuizzesBlocked(List<Quiz> quizzesBlocked) {
        this.quizzesBlocked = quizzesBlocked;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Administrator{");

        sb.append("username='").append(getUsername()).append('\'');
        sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", gender=").append(getGender());
        sb.append(", role=").append(getRole());
        sb.append(", picture='").append(getPicture()).append('\'');
        sb.append(", description='").append(getDescription()).append('\'');
        sb.append(", metadata='").append(getMetadata()).append('\'');
        sb.append(", status=").append(getStatus());
        sb.append(", quizzes=").append(getQuizzes());
        sb.append("quizzesBlocked=").append(quizzesBlocked);
        sb.append('}');
        return sb.toString();
    }
}
