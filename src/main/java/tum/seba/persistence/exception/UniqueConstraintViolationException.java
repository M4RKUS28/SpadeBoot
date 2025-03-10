package tum.seba.persistence.exception;

public class UniqueConstraintViolationException extends RuntimeException {

	public UniqueConstraintViolationException(String message) {
		super(message);
	}

}
