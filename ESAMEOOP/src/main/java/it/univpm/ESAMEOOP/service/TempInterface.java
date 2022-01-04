package it.univpm.ESAMEOOP.service;

import org.json.simple.*;

import it.univpm.ESAMEOOP.model.City;

/**
 * Interfaccia che racchiude i metodi per la gestione dell'API che 
 * sono implementate dalle classi derivate
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public interface TempInterface {
	
	public abstract JSONObject createJSON(City city);
	public abstract JSONObject getDataWeather(long id);
	public abstract City setDataWeather (JSONObject forecast);
	public abstract JSONObject Statistics(String route);
}
