package it.univpm.ESAMEOOP.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataTemp;

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
	
    public JSONObject Compare(JSONObject obj, JSONObject sts) {
    	
    	JSONObject compare = new JSONObject();
    	
    	compare.put("Current Information:", obj);
    	compare.put("History statistics:", sts);
    	
    	return compare;	
    }
}
