package academy.hiperQuiz.quizz.dao;

import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;
import academy.hiperQuiz.quizz.entity.Identifiable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K>> {
    List<V> findAll();

    Optional<V> findById(K id);

    V create(V entity) throws EntityAlreadyExistsException;

    int createBatch(Collection<V> entities) throws EntityAlreadyExistsException;

    V update(V entity) throws EntityNotFoundException;

    V deleteById(K id) throws EntityNotFoundException;
    long count();

    long drop();

    static <K, V extends Identifiable<K>> Repository<K, V> createRepository(Class<K> keyClass, Class<V> entityClass) {
        String implClassName = ENTITY_TO_REPOSITORY_MAP.get(entityClass.getName());
        try {
            Class<V> implClass = (Class<V>) Class.forName(implClassName);
            Constructor constructor;
            try {
                constructor = implClass.getDeclaredConstructor(KeyGenerator.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                constructor = implClass.getDeclaredConstructor();
            }
            String genClassName = KEY_TO_GENERATOR_MAP.get(keyClass.getName());
            Class<KeyGenerator<K>> genClass = (Class<KeyGenerator<K>>) Class.forName(genClassName);
            Constructor<KeyGenerator<K>> genConstructor = genClass.getConstructor();
            return (Repository<K, V>) constructor.newInstance(genConstructor.newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    static final Map<String, String> ENTITY_TO_REPOSITORY_MAP =
            Map.of(
                    "java.course.hiperQuiz.quizz.model.User", "java.course.hiperQuiz.quizz.dao.impl.UserMemoryImpl",
                    "java.course.hiperQuiz.quizz.model.Quiz", "java.course.hiperQuiz.quizz.dao.impl.QuizMemoryImpl",
                    "java.course.hiperQuiz.quizz.model.QuizResult", "java.course.hiperQuiz.quizz.dao.impl.QuizResultMemoryImpl"
            );
    static final Map<String, String> KEY_TO_GENERATOR_MAP =
            Map.of(
                    "java.lang.Long", "java.course.hiperQuiz.quizz.dao.impl.LongKeyGenerator"
            );

}

