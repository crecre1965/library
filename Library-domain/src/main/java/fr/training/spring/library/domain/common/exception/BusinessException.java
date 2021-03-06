package fr.training.spring.library.domain.common.exception;

public class BusinessException extends RuntimeException{
    private final String errorCode;

    protected BusinessException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
