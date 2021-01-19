package fr.training.spring.library.domain.common.exception;

/**
 * Super class of all Business exceptions.
 */
public abstract class BusinessExceptionOld extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessExceptionOld() {
		super();
	}

	public BusinessExceptionOld(final String message, final Throwable cause, final boolean enableSuppression,
								final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessExceptionOld(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessExceptionOld(final String message) {
		super(message);
	}

	public BusinessExceptionOld(final Throwable cause) {
		super(cause);
	}

	public abstract String getCode();

}
