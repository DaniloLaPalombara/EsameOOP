package it.univpm.ESAMEOOP.service;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StatsTemp implements StatsInterface {
	
	private double tmax;
	private double tmin;
	private double temp;
	private Integer num;
	private double average;
	private double variance;
	JSONObject obj =new JSONObject();
	
	public JSONArray Parse(String route) {
		
		JSONParser parser = new JSONParser();
		JSONArray obj = new JSONArray();
		try {
			obj = (JSONArray) parser.parse(route);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray data = (JSONArray) obj;
		
		return data;
	}
	
	@Override
	public double getTempMax(String route) {
		
		JSONArray obj = (JSONArray) Parse(route);
		
		while(obj!=null) {
		
		for(int i=0;i<obj.size();i++) {
			
			JSONObject mario = (JSONObject) obj.get(i);
		
			while(mario != null) {
	
			double tempMax = (double) mario.get("Temp_MAX");
			if(tempMax>tmax) {
				tmax=tempMax;
			}
			}	
		}
			
		}
		
		/*JSONArray weather =(JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++) {
			
			JSONObject TempMax=(JSONObject)weather.get(i);
			double tempMax = (double) TempMax.get("Temp_MAX");
			if(tempMax >tmax) {
				tmax=tempMax;
			}
		}*/
		
		return tmax;		
	}
	
	@Override
	public double getTempMin(JSONObject obj) {
		
		double tmin = 1000000;
		JSONArray weather =(JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++) {
			
			JSONObject TempMin=(JSONObject)weather.get(i);
			double tempMin = (double) TempMin.get("Temp_MIN");
			if(tempMin <tmin) {
				tmin=tempMin;
			}
		}
		
		return tmin;	
	}	
	
	@Override
	public double getAverage(JSONObject obj) {
		
		JSONArray weather =(JSONArray)obj.get("Weather Information:");
		for(int i=0; i<weather.size();i++) {
			
			JSONObject TempAverage=(JSONObject)weather.get(i);
			double tempAverage = (double) TempAverage.get("Temp");
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
			
		return variance =sum/(weather.size()-1);
	}	
}	