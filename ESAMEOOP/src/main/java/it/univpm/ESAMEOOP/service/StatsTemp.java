package it.univpm.ESAMEOOP.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
			obj = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(route)));
			
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray data = (JSONArray) obj;
		
		return data;
	}
	
	@Override
	public double getTempMax(String route) {
		
		JSONArray obj = (JSONArray) Parse(route);
		
		for(int i=0;i<obj.size();i++) {
			
			JSONArray array = (JSONArray) obj.get(i);
		
			for(int j=0;j<array.size();j++) {
				
				JSONObject obj2 = (JSONObject) array.get(j);
				
				double tempMax = (double) obj2.get("Temp_MAX");
				if(tempMax>tmax) {
				tmax=tempMax;
			}
			}	
		}
		
		return tmax;		
	}
	
	@Override
	public double getTempMin(String route) {
		
		tmin = 100;
        JSONArray obj = (JSONArray) Parse(route);
		
		for(int i=0;i<obj.size();i++) {
			
			JSONArray array = (JSONArray) obj.get(i);
		
			for(int j=0;j<array.size();j++) {
				
				JSONObject obj2 = (JSONObject) array.get(j);
				
				double tempMin = (double) obj2.get("Temp_MIN");
				if(tempMin<tmin) {
				tmin=tempMin;
			}
			}	
		}
		
		return tmin;		
	}	
	
	@Override
	public double getAverage(String route) {
		
		 JSONArray obj = (JSONArray) Parse(route);
			
			for(int i=0;i<obj.size();i++) {
				
				JSONArray array = (JSONArray) obj.get(i);
			
				for(int j=0;j<array.size();j++) {
					
					JSONObject obj2 = (JSONObject) array.get(j);
			double tempAverage = (double) obj2.get("Temp");
			temp+=tempAverage;
				}
		}
		
		return average = Math.round((temp/obj.size()*100)/100);	
	}

	@Override
	public double getVariance(String route) {
		
		double sum = 0;
		 JSONArray obj = (JSONArray) Parse(route);
			
			for(int i=0;i<obj.size();i++) {
				
				JSONArray array = (JSONArray) obj.get(i);
			
				for(int j=0;j<array.size();j++) {
					
					JSONObject obj2 = (JSONObject) array.get(j);
			double tempVariance = (double) obj2.get("Temp");
			
			sum += Math.pow((tempVariance-average), 2);
		}
			}
			
		return variance =sum/(obj.size()-1);
	}	
}	