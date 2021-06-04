package quizz.dao;

@FunctionalInterface // SAM
public interface KeyGenerator<K> {
    K getNextId();
}
