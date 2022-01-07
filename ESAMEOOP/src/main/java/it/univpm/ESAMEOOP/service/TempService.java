package it.univpm.ESAMEOOP.service;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.json.simple.*;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import it.univpm.ESAMEOOP.errors.DivisionByZeroException;
import it.univpm.ESAMEOOP.errors.JSONObjectNullException;
import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataTemp;

/**
 * Classe che implementa l'interfaccia, all'interno vi si trovano i metodi
 * per la gestione dell'API
 * @Primary indica la preferenza quando sono presenti più bean dello stesso tipo
 * al fine di evitare ambiguità
 * @Service è un'annotazione che serve per contrassegnare la classe fornitore 
 * di servizi
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
@Primary
@Service
public class TempService implements TempInterface {
		
		private String Apikey="1df4cb04102d63e8af8fa80502fe09ae";
		private String URLCurrent="http://api.openweathermap.org/data/2.5/weather?id=";
		Vector <DataTemp> forecast=new Vector<DataTemp>();
    	int counter=0;
    	
		public int getCounter() {
			return counter;
		}

		/**
		 * Metodo che serve per mettersi in collegamento con l'API e di utilizzare
		 * i dati messi a disposizione
		 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
		 * un metodo che deriva da una superclasse o da un'interfaccia
		 */
		@Override
		public JSONObject getDataWeather(long id) {
			
			JSONObject fullInformation=new JSONObject();
			
			try {
				URLConnection openConnection = new URL(URLCurrent+id+"&units=metric"+"&appid="+Apikey).openConnection();
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
		 * Metodo che prende dai dati forniti dall'API sono quelli di interesse
		 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
		 * un metodo che deriva da una superclasse o da un'interfaccia
		 */
		@Override
		public City setDataWeather(JSONObject fullInformation) throws JSONObjectNullException
		{
			DataTemp data = new DataTemp();
			City city= new City();
			JSONObject cityData = (JSONObject) fullInformation;
			if(cityData == null) {
				throw new JSONObjectNullException("Error: this JSONObjcet is null");
			}
			city.setId((long)cityData.get("id"));
			city.setName((String)cityData.get("name"));
		    data.setDate_UNIX((long)cityData.get("dt"));
		    
			JSONObject country=(JSONObject)cityData.get("sys");
			if(country == null) {
				throw new JSONObjectNullException("Error: this JSONObjcet is null");
			}
			city.setCountry((String)country.get("country"));		
			JSONObject cord = (JSONObject)cityData.get("coord");
			if(cord == null) {
				throw new JSONObjectNullException("Error: this JSONObjcet is null");
			}
			city.setLon((double)cord.get("lon"));
			city.setLat((double)cord.get("lat"));

			JSONObject list = (JSONObject)cityData.get("main");
			if(list == null) {
				throw new JSONObjectNullException("Error: this JSONObjcet is null");
			}
			data.setFeels_like((double)list.get("feels_like"));
			data.setTemp((double)list.get("temp"));
			data.setTemp_MIN((double)list.get("temp_min"));
			data.setTemp_MAX((double)list.get("temp_max"));
			
			JSONArray weather=(JSONArray)cityData.get("weather");
			for(int i = 0; i <weather.size();i++) {
				
				JSONObject List =(JSONObject)weather.get(i);
				data.setWeather((String)List.get("main"));
				data.setWeather_description((String)List.get("description"));	
			}
				forecast.add(data);	
		
			city.setDataTemp(forecast);
			return city;
		}

		/**
		 * Metodo che prende i dati di interesse riguaradanti la città e
	     * le temperature e li utilizza per creare un JSONObject
		 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
		 * un metodo che deriva da una superclasse o da un'interfaccia
		 */
		@Override
		public JSONObject createJSON(City city) {
			
			JSONObject out = new JSONObject();
			out.put("City", city.getName());
			out.put("Id:", city.getId());
			out.put("Lat:", city.getLat());
			out.put("Lon", city.getLon());
			out.put("Country", city.getCountry());
			
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
			obj.put("Temp Information", weather);
			
			return obj;		
		}
		
		/**
		 * Metodo che permette di salvare ogni ora i dati relativi alle
		 * temperature di una città
		 * @param obj Il JSONObject contenente le informazioni sulle temperature
		 * @param id L'indirizzo relativo ad una città
		 * @param call Il numero di chiamate da effettuare
		 * @param interval L'intervallo temporale tra una chiamata e l'altra
		 * @param route Il percorso sul quale il file viene salvato in locale
		 * @return vettore contenente gli elementi utili per il calcolo delle statistiche
		 */
	    public void Saving(JSONObject obj,int call,int interval,String route) {
	    	
	    	File file = new File(route);
	    	FileWriter fileW;

	    	try {
	    		fileW = new FileWriter(file, true);
	    		BufferedWriter buffered = new BufferedWriter(fileW);
	    		buffered.write("[ \n");
	    		buffered.close();
	    		} catch (IOException e1) {
	    			e1.printStackTrace();	
	    		}
	    	
	    	Timer timer = new Timer();
	    	TimerTask timerT = new TimerTask() {
	    		
	    		public void run() {
	    			
	    			try {
	    				JSONArray temp = (JSONArray) obj.get("Temp Information");
	    				
	    				for (int i = 0; i<temp.size();i++) {
	    					counter++;
	    				}
	    				
	    				FileWriter fileW = new FileWriter(file, true);
	    				BufferedWriter buffered2 = new BufferedWriter(fileW);
	    				buffered2.write(temp.toJSONString());
	    				buffered2.write("\n");
	    				buffered2.close();
	    				
	    				if(counter>=call) {
	    					timer.cancel();
	    					
	    					try {
	    						fileW = new FileWriter(file, true);
	    						BufferedWriter buffered3 = new BufferedWriter(fileW);
	    						buffered3.write("]");
	    						buffered3.close();
	    						
	    					} catch (IOException e) {
	    						e.printStackTrace();
	    					}
	    				}
	    				
	    			} catch (IOException e) {
	    				e.printStackTrace();	
	    			}	
	    		}	
	    	};
	    	
	    	timer.schedule(timerT, 0, interval);	
	    }
	
	/**
	 * Metodo che prende i dati riguardanti le statistiche sulle temperature 
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
