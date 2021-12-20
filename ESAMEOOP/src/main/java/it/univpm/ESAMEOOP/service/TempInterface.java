package it.univpm.ESAMEOOP.service;

import org.json.simple.*;
import it.univpm.ESAMEOOP.model.City;

public interface TempInterface {
	
	public abstract JSONObject createJSON(City city);
	public abstract JSONObject getDataWeather(long id);
	public abstract City setDataWeather (JSONObject forecast);
	//public abstract void saveFile(JSONObject obj);
}
