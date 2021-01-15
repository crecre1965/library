package fr.training.spring.library.domain.exception;

public class NotFoundException extends RuntimeException{

    private final String errorCode;

    public NotFoundException(final String message,  final String errorCode) {
        super(message + errorCode);
        this.errorCode=errorCode;

    }

    public String getErrorCode(){
        return errorCode;
    }

}
