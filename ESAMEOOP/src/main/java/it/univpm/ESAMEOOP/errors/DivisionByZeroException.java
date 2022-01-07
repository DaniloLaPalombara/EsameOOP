package it.univpm.ESAMEOOP.errors;

public class DivisionByZeroException extends Exception {
	
	public DivisionByZeroException() {
		super();
	}
	
	public DivisionByZeroException(String msg) {
		super(msg);
	}
}
