package it.univpm.ESAMEOOP.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.ESAMEOOP.errors.DivisionByZeroException;

/**
 * Classe che implementa l'interfaccia, all'interno vi si trovano i metodi
 * per il calcolo dei valori riguardanti le statistiche sulle temperature percepite
 * @Service è un'annotazione che serve per contrassegnare la classe fornitore 
 *         di servizi
 * @author daniloLaPalombara&nicolòIanni
 *
 */
@Service
public class StatsFeelsLike implements StatsInterface {
	
	private double tmax;
	private double tmin;
	private double temp;
	private Integer num;
	private double average;
	private double variance;
	JSONObject obj = new JSONObject();
	
	
	public JSONArray Parse(String route) {
		
		JSONParser parser = new JSONParser();
		JSONArray obj = new JSONArray();
		try {
			obj = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(route)));
			
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} 
		
		JSONArray data = (JSONArray) obj;
		
		return data;
	}
	
	/**
	 * Metodo che calcola la temperatura massima percepita di una città
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 *          un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public double getTempMax(String route) {
		
		JSONArray obj = (JSONArray) Parse(route);
		
		for(int i=0;i<obj.size();i++) {
			
			JSONArray array = (JSONArray) obj.get(i);
		
			for(int j=0;j<array.size();j++) {
				
				JSONObject obj2 = (JSONObject) array.get(j);
				
				double tempMax = (double) obj2.get("Feels_like");
				
				if(tempMax>tmax) {
				tmax = tempMax;
				}
			}	
		}
		
		return tmax;		
	}
	
	/**
	 * Metodo che calcola la temperatura minima percepita di una città
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 *          un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public double getTempMin(String route) {
		
		tmin = 100;
        JSONArray obj = (JSONArray) Parse(route);
		
		for(int i=0;i<obj.size();i++) {
			
			JSONArray array = (JSONArray) obj.get(i);
		
			for(int j=0;j<array.size();j++) {
				
				JSONObject obj2 = (JSONObject) array.get(j);
				
				double tempMin = (double) obj2.get("Feels_like");
				
				if(tempMin<tmin) {
				tmin=tempMin;
				}
			}	
		}
		
		return tmin;		
	}	
	
	/**
	 * Metodo che calcola la temperatura media percepita di una città
	 * @throws DivisionByZeroException Gestione dell'errore nel caso in cui il denominatore sia zero
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 *          un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public double getAverage(String route) throws DivisionByZeroException {
		
		JSONArray obj = (JSONArray) Parse(route);
		
		for(int i=0;i<obj.size();i++) {
			
			JSONArray array = (JSONArray) obj.get(i);
			
			for(int j=0;j<array.size();j++) {
				
				JSONObject obj2 = (JSONObject) array.get(j);
				double tempAverage = (double) obj2.get("Temp");
				temp+=tempAverage;	
			}	
		}
		
		if(obj.size()==0) {
			 
			 throw new DivisionByZeroException("ERROR: division by zero is impossible"); 
		 }
		
		return average = Math.round((temp/obj.size()*100)/100);
	}

	/**
	 * Metodo che calcola la varianza della temperatura percepita di una città
	 * @throws DivisionByZeroException Gestione dell'errore nel caso in cui il denominatore sia zero
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 *          un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public double getVariance(String route) throws DivisionByZeroException {
		
		double sum = 0;
		JSONArray obj = (JSONArray) Parse(route);
		
		for(int i=0;i<obj.size();i++) {
			
			JSONArray array = (JSONArray) obj.get(i);
			
			for(int j=0;j<array.size();j++) {
				
				JSONObject obj2 = (JSONObject) array.get(j);
				double tempVariance = (double) obj2.get("Feels_like");
				sum += Math.pow((tempVariance-average), 2);	
			}
		}
		
		if(obj.size()==0) {
			 
			 throw new DivisionByZeroException("ERROR: division by zero is impossible"); 
		 }
			
		return variance = Math.round((sum/(obj.size()-1)*100)/100);
	}	
}
