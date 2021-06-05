package quizz.model;

import java.io.Serializable;
import java.util.List;

public class Question extends AbstractEntity<Long, Question> implements Serializable {

    private Quiz quiz;
    private String text;
    private String picture;
    private List <Answer> answers;

    public Question() {
    }

    public Question(String text) {
        this.text = text;
    }

    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    public Question(Quiz quiz, String text, List<Answer> answers) {
        this.quiz = quiz;
        this.text = text;
        this.answers = answers;
    }
    public Question(Long id, Quiz quiz, String text, String picture, List<Answer> answers) {
        super(id);
        this.quiz = quiz;
        this.text = text;
        this.picture = picture;
        this.answers = answers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Question{");
        sb.append("quiz=").append(quiz);
        sb.append(", text='").append(text).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", answers=").append(answers);
        sb.append('}');
        return sb.toString();
    }
}
