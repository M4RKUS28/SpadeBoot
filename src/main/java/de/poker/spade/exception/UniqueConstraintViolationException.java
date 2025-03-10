package de.poker.spade.exception;

public class UniqueConstraintViolationException extends RuntimeException {

	public UniqueConstraintViolationException(String message) {
		super(message);
	}

}
