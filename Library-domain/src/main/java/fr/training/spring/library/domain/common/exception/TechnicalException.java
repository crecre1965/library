package fr.training.spring.library.domain.common.exception;

import org.springframework.web.client.HttpStatusCodeException;

public class TechnicalException extends RuntimeException{
    public TechnicalException(final HttpStatusCodeException e){
        super(e);
    }
}
