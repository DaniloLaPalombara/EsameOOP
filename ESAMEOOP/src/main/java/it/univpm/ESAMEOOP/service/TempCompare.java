package it.univpm.ESAMEOOP.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataTemp;

/**
 * Metodo che permette di confrontare le statistiche delle temperature in
 * una fascia oraria con le temperature correnti
 * @Service è un'annotazione che serve per contrassegnare la classe fornitore 
 * di servizi
 * @author daniloLaPalombara&nicoloIanni
 *
 */
@Service
public class TempCompare {
	
	private double temp;
	private double feels_like;
	private double temp_MIN;
	private double temp_MAX;
	private double tmax;
	private double tmin;
	private double average;
	private double variance;
	
	String route = System.getProperty("user.dir") + "/src/main/resources/" + "HistoryStatistics";
	
	public String getRoute() {
		return route;
	}

	public JSONObject CompareCurrent(City city) {
		
		JSONArray weather = new JSONArray();
				
		for(DataTemp data:city.getDataTemp()) {
		
		JSONObject WeatherData = new JSONObject();
		WeatherData.put("Date", data.getDate());
		WeatherData.put("Feels_like", data.getFeels_like());
		WeatherData.put("Temp", data.getTemp());
		WeatherData.put("Temp_MAX", data.getTemp_MAX());
		WeatherData.put("Temp_MIN", data.getTemp_MIN());
		
		weather.add(WeatherData);	
	    }
		
		JSONObject obj = new JSONObject();
		   
		obj.put("Current Information", weather);
		
		return obj;
	}
	
	/**
	 * Metodo che racchiude in un unico JSONObject i dati sulla
	 * temeperatura corrente e quelli sullo storico
	 * @return
	 */
    public JSONObject Compare(JSONObject obj, JSONObject sts) {
    	
    	JSONObject compare = new JSONObject();
    	
    	compare.put("Temp Information:", obj);
    	compare.put("History statistics:", sts);
    	
    	return compare;	
    }
}
