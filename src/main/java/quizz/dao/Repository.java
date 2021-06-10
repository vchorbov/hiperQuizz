package quizz.dao;
import quizz.exception.EntityAlreadyExistsException;
import quizz.exception.EntityNotFoundException;
import quizz.model.Identifiable;

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

    void drop();

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
                    "quizz.model.User", "quizz.dao.impl.UserMemoryImpl",
                    "quizz.model.Quiz", "quizz.dao.impl.QuizMemoryImpl",
                    "quizz.model.QuizResult", "quizz.dao.impl.QuizResultMemoryImpl"
            );
    static final Map<String, String> KEY_TO_GENERATOR_MAP =
            Map.of(
                    "java.lang.Long", "quizz.dao.impl.LongKeyGenerator"
            );

}

