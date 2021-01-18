package fr.training.spring.library.domain.exception;

public class NotFoundException extends BusinessException
{


    public NotFoundException(final String message, final String errorCode) {

        super(message,errorCode);
    }


}
