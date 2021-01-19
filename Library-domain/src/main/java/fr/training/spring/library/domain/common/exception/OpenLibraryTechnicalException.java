package fr.training.spring.library.domain.common.exception;



import org.springframework.web.client.HttpStatusCodeException;

public class OpenLibraryTechnicalException extends TechnicalException        {
    public OpenLibraryTechnicalException(final HttpStatusCodeException e) {
       super(e);

    }
}
