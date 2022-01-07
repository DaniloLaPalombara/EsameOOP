package it.univpm.ESAMEOOP.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.ESAMEOOP.errors.DivisionByZeroException;
import it.univpm.ESAMEOOP.errors.JSONObjectNullException;
import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataTemp;

/**
 * Classe che implementa l'interfaccia, all'interno vi si trovano i metodi
 * per la gestione dell'API
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
	protected String route = System.getProperty("user.dir") + "/src/main/resources/" + "HourlyStatistics";
	
	public String getRoute() {
		return route;
	}

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
		
		JSONObject fullInformation = new JSONObject();
		
		try {
			URLConnection openConnection = new URL(URLHistory+id+"&type="+getType()+"&start="+getStart()+"&end="+getStop()+"&appid="+Apikey).openConnection();
			InputStream in = openConnection.getInputStream();
			
			String data = " ";
			String line = " ";
			
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
	 * metodo che prende dai dati forniti dall'API solo quelli di interesse
	 * @throws JSONObjectNullException Gestione dell'errore nel caso un JSONObject sia nullo
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 * un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public City setDataWeather(JSONObject fullInformation) throws JSONObjectNullException {
		
		City city=new City();
		JSONObject info=(JSONObject)fullInformation;
		if(info == null) {
			throw new JSONObjectNullException("Error: this JSONObjcet is null");
		}
		city.setId((long)info.get("city_id"));
		
		JSONArray list = (JSONArray)info.get("list");
		if(list == null) {
			throw new JSONObjectNullException("Error: this JSONObjcet is null");
		}
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
	 * Metodo che prende i dati di interesse riguaradanti la città e lo storico
	 * delle temperature e li utilizza per creare un JSONObject
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 * un metodo che deriva da una superclasse o da un'interfaccia
	 */
	@Override
	public JSONObject createJSON(City city) {
		
		JSONObject out = new JSONObject();
		out.put("Id:", city.getId());
		
		JSONArray weather = new JSONArray();
		for(DataTemp data:city.getDataTemp()) {
			
			JSONArray array = new JSONArray();
			JSONObject WeatherData = new JSONObject();
			WeatherData.put("Date", data.getDate());
			WeatherData.put("Feels_like", Math.round((data.getFeels_like()-273.15)*100)/100);
			WeatherData.put("Temp", (data.getTemp()-273.15));
			WeatherData.put("Temp_MAX", (data.getTemp_MAX()-273.15));
			WeatherData.put("Temp_MIN", (data.getTemp_MIN()-273.15));
			
			weather.add(WeatherData);
		}
		
		JSONObject obj = new JSONObject();
	    obj.put("City Information", out);      
		obj.put("Temp Information:", weather);
		
		return obj;
	}
	
	/**
	 * metodo che raccoglie i dati riguardanti lo storico sulle temperature
	 * correnti e percepite in un file appositamente modellato per il successivo
	 * calcolo delle statistiche 
	 * @param obj Il JSONObject contenente
	 * @param route Il percorso sul quale il file viene salvato in locale
	 */
	public void Saving(JSONObject obj, String route) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(route));
			writer.write("[ " + "\n");
			JSONArray weather = (JSONArray)obj.get("Temp Information:");
			
			for (int i = 0; i<weather.size();i++) {
			        
			        JSONObject Temp = (JSONObject)weather.get(i);
					DataTemp data= new DataTemp();
			        data.setTemp((double) Temp.get("Temp"));
				    data.setTemp_MIN((double) Temp.get("Temp_MIN"));
				    data.setTemp_MAX((double) Temp.get("Temp_MAX"));
				    data.setFeels_like((long) Temp.get("Feels_like"));
				    writer.write("[");
				    writer.write("{");
				    writer.write('"' + "Temp"+ '"'+": "+data.getTemp()+",");
				    writer.write('"' +"Temp_MIN"+ '"'+": "+data.getTemp_MIN()+",");
				    writer.write('"'+"Temp_MAX"+ '"'+ ": "+data.getTemp_MAX()+",");
				    writer.write('"'+"Feels_like"+'"'+ ": "+data.getFeels_like()+",");
				    writer.write("}");
				    writer.write("]" + "\n");
			}
			writer.write("\n"+"]");
			writer.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che prende i dati riguardanti le statistiche sullo storico delle temperature 
	 * correnti e percepite e li utilizza per creare un JSONObject
	 * @throws DivisionByZeroException 
	 */
	public JSONObject Statistics(String route) throws DivisionByZeroException {
		
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
		
		JSONObject sts = new JSONObject();
	    sts.put("Temperature statistics", statsT);      
		sts.put("Feels like statistics:", statsFL);
		
		return sts;
	}
}
