package fr.training.spring.library.domain.common.exception;

public class EntityNotFoundExceptionOld extends BusinessExceptionOld {

	private static final long serialVersionUID = -1L;

	public EntityNotFoundExceptionOld(final String message) {
		super(message);
	}

	public EntityNotFoundExceptionOld() {
		super();
	}

	@Override
	public String getCode() {
		return "func.err.01";
	}
}
