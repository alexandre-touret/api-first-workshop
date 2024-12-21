package info.touret.guitarheaven.domain.exception;

public class GuitarOrderException extends RuntimeException {
    public GuitarOrderException() {
        super();
    }

    public GuitarOrderException(String message) {
        super(message);
    }

    public GuitarOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public GuitarOrderException(Throwable cause) {
        super(cause);
    }

    protected GuitarOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
