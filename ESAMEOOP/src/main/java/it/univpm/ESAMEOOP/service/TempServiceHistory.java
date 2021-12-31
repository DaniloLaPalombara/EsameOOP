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
		obj.put("Temp Information:", weather);
		
		return obj;
	}
	
	/*public void saveFile(JSONObject obj)
	{
	try {
		 File dbOrig = new File("C:\\Users\\Nicoló\\git\\EsameOOP\\ESAMEOOP\\src\\main\\java\\it\\univpm\\ESAMEOOP\\file.txt");
	     File dbCopy = new File("C:\\Users\\Nicoló\\git\\EsameOOP\\ESAMEOOP\\src\\main\\java\\it\\univpm\\ESAMEOOP\\file.json");
	     Files.copy( dbOrig.toPath(), dbCopy.toPath() );
	     
	     
	     /*InputStream in = new FileInputStream(dbOrig);
	     OutputStream out = new FileOutputStream(dbCopy);
	     byte[] buf = new byte[1024];
	     int len;
	     while ((len = in.read(buf)) > 0) {
	        out.write(buf, 0, len);
	     }
	     in.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter
			    (dbCopy));//"C:\\Users\\Nicoló\\git\\EsameOOP\\ESAMEOOP\\src\\main\\java\\it\\univpm\\ESAMEOOP\\file.txt"));
		JSONArray weather = (JSONArray)obj.get("Weather Information:");
		for (int i = 0; i<weather.size();i++)
		{
		        
		        JSONObject Temp = (JSONObject)weather.get(i);
				DataTemp data= new DataTemp();
		        data.setTemp((double) Temp.get("Temp"));
			    data.setTemp_MIN((double) Temp.get("Temp_MIN"));
			    data.setTemp_MAX((double) Temp.get("Temp_MAX"));
			    data.setFeels_like((double) Temp.get("Feels_like"));
			    writer.write("Temp: "+data.getTemp()+"\n");
			    writer.write("Temp_MIN: "+data.getTemp_MIN()+"\n");
			    writer.write("Temp_MAX: "+data.getTemp_MAX()+"\n");
			    writer.write("Feels_like: "+data.getFeels_like()+"\n");
			    
			  //  out.close();
		   


		}
		writer.close();

		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
}
