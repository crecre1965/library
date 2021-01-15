    package fr.training.spring.library.exposition.exception;

    import fr.training.spring.library.domain.exception.NotFoundException;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.bind.annotation.ResponseStatus;
    import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



    @ControllerAdvice
    public class LibraryResourceExceptionHandler extends ResponseEntityExceptionHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(LibraryResourceExceptionHandler.class);

        @ResponseBody
        @ExceptionHandler(NotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String NotFound(final NotFoundException notFoundException){
            final String codeErreur= notFoundException.getErrorCode();
            LOGGER.info("Error {} : {}",codeErreur,notFoundException.getMessage());
            return codeErreur;
        }

    }
