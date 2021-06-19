package academy.hiperQuiz.quizz.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="quizzes")
public class Quiz extends AbstractEntity<Long, Quiz> {

    @NotNull
    @Size(min=2, max=80)
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    private String title; // 2 to 80 characters long;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private User author; // the User that created the Quiz;
    @NotNull
    @Size(min=20, max=250)
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String description; // 20 - 250 characters long, supporting Markdown syntax;
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions; //list of Question entities (containing the answers with their scores too);
    @Column(name = "expected_duration")
    @NotNull
    private int expectedDuration; //integer number in minutes;
    private String picture; // (optional) - best representing the Quiz, valid URL to a picture, if missing a placeholder picture should be used;
    private String tags; // - string including comma separated tags, allowing to find the Quizes by quick search;

    public Quiz() {
    }

    public Quiz(String title, User author, String description, List<Question> questions, int expectedDuration) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.questions = questions;
        this.expectedDuration = expectedDuration;
    }
    public Quiz(String title, User author, String description, int expectedDuration) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.expectedDuration = expectedDuration;
    }

    public Quiz(Long id, String title, User author, String description, List<Question> questions, int expectedDuration, String tags) {
        super(id);
        this.title = title;
        this.author = author;
        this.description = description;
        this.questions = questions;
        this.expectedDuration = expectedDuration;
        this.tags = tags;
    }

    public Quiz(Long id, String title, User author, String description, List<Question> questions, int expectedDuration, String picture, String tags) {
        super(id);
        this.title = title;
        this.author = author;
        this.description = description;
        this.questions = questions;
        this.expectedDuration = expectedDuration;
        this.picture = picture;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Quiz{");
        sb.append("title='").append(title).append('\'');
        sb.append(", author=").append(author.getUsername());
        sb.append(", description='").append(description).append('\'');
        sb.append(", questions=").append(questions.get(0));
        sb.append(", expectedDuration=").append(expectedDuration);
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", tags='").append(tags).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
