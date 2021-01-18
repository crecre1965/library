package fr.training.spring.library.domain.exception;

public class ValidationException extends BusinessException {
    public ValidationException(final String message, final String errorCode) {
        super(message, errorCode);
    }
}
