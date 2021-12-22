package it.univpm.ESAMEOOP.service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class StatsFeelsLike implements StatsInterface {
	
	private double tmax;
	private double tmin;
	private double temp;
	private Integer num;
	private double average;
	private double variance;
	JSONObject obj =new JSONObject();
	
	@Override
	public double getTempMax(JSONObject obj) {
		
		JSONArray weather =(JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++)
		{
			JSONObject TempMax=(JSONObject)weather.get(i);
			double tempMax = (double) TempMax.get("Feels_like");
			if(tempMax >tmax)
			{
				tmax=tempMax;
			}
		}
		
		return tmax;		
	}
	
	@Override
	public double getTempMin(JSONObject obj) {
		
		double tmin = 1000000;
		JSONArray weather =(JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++)
		{
			JSONObject TempMin=(JSONObject)weather.get(i);
			double tempMin = (double) TempMin.get("Feels_like");
			if(tempMin <tmin)
			{
				tmin=tempMin;
			}
		}
		
		return tmin;	
	}	
	
	@Override
	public double getAverage(JSONObject obj) {
		
		int counter = 0;
		JSONArray weather =(JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++)
		{
			JSONObject TempAverage=(JSONObject)weather.get(i);
			double tempAverage = (double) TempAverage.get("Feels_like");
				temp+=tempAverage;
		}
		
		return average = Math.round((temp/weather.size()*100)/100);
	}

	@Override
	public double getVariance(JSONObject obj) {
		
		double sum = 0;
		JSONArray weather = (JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++) {
			
			JSONObject TempVariance =(JSONObject)weather.get(i);
			double tempVariance = (double) TempVariance.get("Temp");
			
			sum += Math.pow((tempVariance-average), 2);
		}		
			
		return variance = sum/(weather.size()-1);	
	}	
}
