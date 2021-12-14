package it.univpm.ESAMEOOP.service;

import org.json.simple.*;
import it.univpm.ESAMEOOP.model.City;

public interface OpenWeatherInterface {
	public abstract JSONObject createJSON(City city);
	public abstract JSONObject getDataWeather(long id);
	public abstract City setDataWeather (JSONObject dataWeather);
	public abstract void saveFile(JSONObject obj);
}
