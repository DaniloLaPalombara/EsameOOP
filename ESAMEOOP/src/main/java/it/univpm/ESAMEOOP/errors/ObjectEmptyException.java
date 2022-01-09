package it.univpm.ESAMEOOP.errors;

/**
 * Classe per gestire il caso in cui un Object sia nullo
 * @author danil
 *
 */
public class ObjectEmptyException extends Exception {
	
	public ObjectEmptyException() {
		super();
	}
	
	public ObjectEmptyException(String msg) {
		super(msg);
	}

}
