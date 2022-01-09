package it.univpm.ESAMEOOP.errors;

/**
 * Classe per la gestione del caso in cui un JSONObject sia nullo
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public class JSONObjectNullException extends Exception {
	
	public JSONObjectNullException() {
		super();
	}
	
	public JSONObjectNullException(String msg) {
		super(msg);
	}
	
}
