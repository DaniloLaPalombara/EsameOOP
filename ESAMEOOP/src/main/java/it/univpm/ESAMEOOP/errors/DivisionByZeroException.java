package it.univpm.ESAMEOOP.errors;

/**
 * Classe per la gestione del caso in cui avvenga una divisione per zero
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public class DivisionByZeroException extends Exception {
	
	public DivisionByZeroException() {
		super();
	}
	
	public DivisionByZeroException(String msg) {
		super(msg);
	}
}
