package it.univpm.ESAMEOOP.service;

import it.univpm.ESAMEOOP.model.City;

public interface OpenWeatherInterface {
	//TODO creare metodi per prendere le previsioni da API e creare JSON parametri meteo astratti
	private abstract JSONObject createJSON(City city);
	private abstract JSONObject getDataWeather(long id);
	private abstract City setDataWeather (JSONObject dataWeather);
	private abstract void saveFile(JSONObject obj);

}
