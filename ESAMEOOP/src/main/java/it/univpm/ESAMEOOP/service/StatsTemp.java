package it.univpm.ESAMEOOP.service;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import it.univpm.ESAMEOOP.model.DataTemp;

@Component
public class StatsTemp implements StatsInterface {
	
	private double tmax;
	private double tmin;
	private double temp;
	private Integer num;
	private Vector<Double> values;
	JSONObject obj =new JSONObject();
	
	@Override
	public double getTempMax(JSONObject obj) {
		
		JSONArray weather =(JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++)
		{
			JSONObject TempMax=(JSONObject)weather.get(i);
			double tempMax = (double) TempMax.get("Temp_MAX");
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
			double tempMin = (double) TempMin.get("Temp_MIN");
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
			double tempAverage = (double) TempAverage.get("Temp");
				temp+=tempAverage;
		}
		
		return Math.round((temp/weather.size()*100)/100);	
	}

	@Override
	public double getVariance(JSONObject obj) {
		// TODO Auto-generated method stub
		return 0;
	}
}	