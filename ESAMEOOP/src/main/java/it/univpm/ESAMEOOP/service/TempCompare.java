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
	
	JSONObject obj;
	JSONObject sts;
	
	public void/*JSONObject*/ CompareCurrent(City city) {
		
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
		   
		obj.put("Current Information", weather);
		
		//return obj;
	}
	
    public void/*JSONObject*/ CompareHistory (String route) {
		
		StatsTemp StatsT = new StatsTemp();
		JSONObject statsT = new JSONObject(); 
		
		statsT.put("Temp_MAX", StatsT.getTempMax(route));
		statsT.put("Temp_MIN",StatsT.getTempMin(route));
		statsT.put("Average", StatsT.getAverage(route));
		statsT.put("Variance", StatsT.getVariance(route));
		
		StatsFeelsLike StatsFL = new StatsFeelsLike();
		JSONObject statsFL = new JSONObject();
		
		statsFL.put("Temp_MAX",  StatsFL.getTempMax(route));
		statsFL.put("Temp_MIN",StatsFL.getTempMin(route));
		statsFL.put("Average", StatsFL.getAverage(route));
		statsFL.put("Variance", StatsFL.getVariance(route));
		
	    sts.put("Temperature statistics:", statsT);      
		sts.put("Feels like statistics:", statsFL);
		
		//return sts;
	}
    
    public JSONObject Compare(JSONObject obj, JSONObject sts) {
    	
    	JSONObject compare = new JSONObject();
    	
    	compare.put("Current Information:", obj);
    	compare.put("History statistics:", sts);
    	
    	return compare;	
    }
}
