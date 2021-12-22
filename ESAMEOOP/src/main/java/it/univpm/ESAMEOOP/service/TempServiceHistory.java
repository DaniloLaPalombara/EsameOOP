package it.univpm.ESAMEOOP.service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataTemp;

/**
 * @Service è un'annotazione che serve per contrassegnare la classe fornitore 
 * di servizi
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */

@Service
public class TempServiceHistory implements TempInterface {
	
	private String type;
	private long start;
	private long stop;
	private String URLHistory="http://history.openweathermap.org/data/2.5/history/city?id=";
	private String Apikey="1df4cb04102d63e8af8fa80502fe09ae";

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public long getStart() {
		return start;
	}


	public void setStart(long start) {
		this.start = start;
	}


	public long getStop() {
		return stop;
	}


	public void setStop(long stop) {
		this.stop = stop;
	}

	/**
	 * metodo che serve per mettersi in collegamento con l'API e di utilizzare
	 * i dati messi a disposizione
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 * un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public JSONObject getDataWeather(long id) {
		
		JSONObject fullInformation=new JSONObject();
		
		try {
			URLConnection openConnection = new URL(URLHistory+id+"&type="+getType()+"&start="+getStart()+"&end="+getStop()+"&appid="+Apikey).openConnection();
			InputStream in = openConnection.getInputStream();
			
			String data= " ";
			String line=" ";
			
	        try {
	        	InputStreamReader reader= new InputStreamReader(in);
	        	BufferedReader buf = new BufferedReader(reader);
	        	
	        	while((line=buf.readLine())!= null) {
	        		data+=line;
	        	}
	        } finally {
	        	in.close();
			}
	        
	        fullInformation = (JSONObject) JSONValue.parseWithException(data);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return fullInformation;
    }	

	/**
	 * metodo che prende dai dati forniti dall'API sono quelli di interesse
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 * un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public City setDataWeather(JSONObject fullInformation) {
		
		City city=new City();
		JSONObject info=(JSONObject)fullInformation;
		city.setId((long)info.get("city_id"));
		
		JSONArray list = (JSONArray)info.get("list");
		Vector <DataTemp> forecast = new Vector<>();
		
		for(int i = 0; i < list.size();i++) {
			
			JSONObject List = (JSONObject)list.get(i);
			DataTemp data = new DataTemp();
		    data.setDate_UNIX((long)List.get("dt"));
		    
			JSONObject main = (JSONObject)List.get("main");
			data.setFeels_like((double)main.get("feels_like"));
			data.setTemp((double)main.get("temp"));
			data.setTemp_MIN((double)main.get("temp_min"));
			data.setTemp_MAX((double)main.get("temp_max"));
			
			forecast.add(data);
			List.clear();	
		}

		city.setDataTemp(forecast);
		return city;
	}
	
	/**
	 * metodo che prende i dati di interesse e li utilizza per creare il 
	 * JSONObject che rescituirà la chiamata tramite controller
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 * un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public JSONObject createJSON(City city) {
		
		JSONObject out = new JSONObject();
		out.put("Id:", city.getId());
		
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
	    obj.put("City Information", out);      
		obj.put("Weather Information:", weather);
		
		return obj;
	}
	
	/**
	 * metodo che prende i dati riguardanti le statistiche e li utilizza per
	 * creare un JSONObject
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 * un metodo che deriva da una superclasse o da un'interfaccia
	 */
	public JSONObject Statits(JSONObject obj) {
		
		StatsTemp StatsT = new StatsTemp();
		JSONObject statsT = new JSONObject(); 
		
		statsT.put("Temp_MAX",  StatsT.getTempMax(obj));
		statsT.put("Temp_MIN",StatsT.getTempMin(obj));
		statsT.put("Average", StatsT.getAverage(obj));
		statsT.put("Variance", StatsT.getVariance(obj));
		
		StatsFeelsLike StatsFL = new StatsFeelsLike();
		JSONObject statsFL = new JSONObject();
		
		statsFL.put("Temp_MAX",  StatsFL.getTempMax(obj));
		statsFL.put("Temp_MIN",StatsFL.getTempMin(obj));
		statsFL.put("Average", StatsFL.getAverage(obj));
		statsFL.put("Variance", StatsFL.getVariance(obj));
		
		JSONObject sts = new JSONObject();
	    sts.put("Temperature statistics", statsT);      
		sts.put("Feels like statistics:", statsFL);
		
		return sts;
	}
}
