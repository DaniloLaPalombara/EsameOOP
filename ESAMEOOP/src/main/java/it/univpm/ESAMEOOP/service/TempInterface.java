package it.univpm.ESAMEOOP.service;

import org.json.simple.*;

import it.univpm.ESAMEOOP.errors.DivisionByZeroException;
import it.univpm.ESAMEOOP.errors.JSONObjectNullException;
import it.univpm.ESAMEOOP.errors.ObjectEmptyException;
import it.univpm.ESAMEOOP.model.City;

/**
 * Interfaccia che racchiude i metodi per la gestione dell'API che 
 * sono implementate dalle classi derivate
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public interface TempInterface {
	
	public abstract JSONObject createJSON(City city) throws JSONObjectNullException;
	public abstract JSONObject GetData(long id) throws JSONObjectNullException;
	public abstract City setData (JSONObject forecast) throws JSONObjectNullException, ObjectEmptyException;
	public abstract JSONObject Statistics(String route) throws DivisionByZeroException;
}
