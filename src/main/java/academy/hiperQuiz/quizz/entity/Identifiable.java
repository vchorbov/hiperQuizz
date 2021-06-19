package academy.hiperQuiz.quizz.entity;

public interface Identifiable<K> {
    K getId();
    void setId(K id);
}
