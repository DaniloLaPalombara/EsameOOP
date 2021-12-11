package it.univpm.ESAMEOOP.service;

import org.json.simple.*;
import it.univpm.ESAMEOOP.model.City;

public interface OpenWeatherInterface {
	//TODO creare metodi per prendere le previsioni da API e creare JSON parametri meteo astratti
	public abstract JSONObject createJSON(City city);
	public abstract JSONObject getDataWeather(long id);
	public abstract City setDataWeather (JSONObject dataWeather);
	public abstract void saveFile(JSONObject obj);

}
