package academy.hiperQuiz.quizz.exception;

public class EntityDeletionException extends RuntimeException {
    public EntityDeletionException() {
    }

    public EntityDeletionException(String message) {
        super(message);
    }

    public EntityDeletionException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityDeletionException(Throwable cause) {
        super(cause);
    }
}
