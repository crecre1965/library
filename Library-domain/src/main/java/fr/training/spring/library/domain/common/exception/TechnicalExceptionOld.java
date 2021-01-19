package fr.training.spring.library.domain.common.exception;

public class TechnicalExceptionOld extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TechnicalExceptionOld() {
		super();
	}

	public TechnicalExceptionOld(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TechnicalExceptionOld(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalExceptionOld(String message) {
		super(message);
	}

	public TechnicalExceptionOld(Throwable cause) {
		super(cause);
	}

}
