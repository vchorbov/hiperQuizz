package quizz.dao;

import java.io.Serializable;

@FunctionalInterface // SAM
public interface KeyGenerator<K> extends Serializable {
    K getNextId();
}
