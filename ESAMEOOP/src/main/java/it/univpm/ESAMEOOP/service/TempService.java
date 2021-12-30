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
		String route = System.getProperty("user.dir") + "/src/main/resources/" + "Ciao";

		
		public String getRoute() {
			return route;
		}

		/**
		 * metodo che serve per mettersi in collegamento con l'API e di utilizzare
		 * i dati messi a disposizione
		 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
		 * un metodo che deriva da una superclasse o da un'interfaccia
		 */
		@Override
		public JSONObject getDataWeather(long id)
		{
			JSONObject fullInformation=new JSONObject();
			
			try {
				URLConnection openConnection = new URL(URLCurrent+id+"&units=metric"+"&appid="+Apikey).openConnection();
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
		public City setDataWeather(JSONObject fullInformation)
		{
			DataTemp data = new DataTemp();
			City city= new City();
			JSONObject cityData = (JSONObject) fullInformation;
			city.setId((long)cityData.get("id"));
			city.setName((String)cityData.get("name"));
		    data.setDate_UNIX((long)cityData.get("dt"));
		    
			JSONObject country=(JSONObject)cityData.get("sys");
			city.setCountry((String)country.get("country"));		
			JSONObject cord = (JSONObject)cityData.get("coord");
			city.setLon((double)cord.get("lon"));
			city.setLat((double)cord.get("lat"));

			JSONObject list = (JSONObject)cityData.get("main");
			//Vector <DataTemp> forecast=new Vector<DataTemp>();
			data.setFeels_like((double)list.get("feels_like"));
			data.setTemp((double)list.get("temp"));
			data.setTemp_MIN((double)list.get("temp_min"));
			data.setTemp_MAX((double)list.get("temp_max"));
			
			JSONArray weather=(JSONArray)cityData.get("weather");
			for(int i = 0; i <weather.size();i++)
			{
				JSONObject List =(JSONObject)weather.get(i);
				data.setWeather((String)List.get("main"));
				data.setWeather_description((String)List.get("description"));
				
			}
				forecast.add(data);	
		
			city.setDataTemp(forecast);
			return city;
		}

		/**
		 * metodo che prende i dati di interesse e li utilizza per creare il 
		 * JSONObject che restituirà la chiamata tramite controller
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
			
			for(DataTemp data:city.getDataTemp())
			{
				JSONObject WeatherData = new JSONObject();
				WeatherData.put("Date", data.getDate());
				WeatherData.put("Feels_like", data.getFeels_like());
				WeatherData.put("Temp", data.getTemp());
				WeatherData.put("Temp_MAX", data.getTemp_MAX());
				WeatherData.put("Temp_MIN", data.getTemp_MIN());
				WeatherData.put("Weather:", data.getWeather());
				WeatherData.put("Descriprion:", data.getWeather_description());
				
				weather.add(WeatherData);
				
			}
			JSONObject obj = new JSONObject();
		    obj.put("City Information", out);      
			obj.put("Weather Information", weather);
			
			return obj;		
		}
		
		/*public void saveFile(JSONObject obj)
		{
		try {
			 File dbOrig = new File("C:\\Users\\Nicoló\\git\\EsameOOP\\ESAMEOOP\\src\\main\\java\\it\\univpm\\ESAMEOOP\\file.txt");
		     File dbCopy = new File("C:\\Users\\Nicoló\\git\\EsameOOP\\ESAMEOOP\\src\\main\\java\\it\\univpm\\ESAMEOOP\\file.json");
		     Files.copy( dbOrig.toPath(), dbCopy.toPath() );
		     
		     
		   //  /*InputStream in = new FileInputStream(dbOrig);
		     OutputStream out = new FileOutputStream(dbCopy);
		     byte[] buf = new byte[1024];
		     int len;
		     while ((len = in.read(buf)) > 0) {
		        out.write(buf, 0, len);
		     }
		     in.close(); QUA VA RIMESSO IL COMMENTO 
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
	
	public Vector HourlySaving(JSONObject obj, long id) {
		
		
		File file = new File(route);
		FileWriter fileW;
		Vector forecast = new Vector();
		
		try {
			fileW = new FileWriter(file, true);
			BufferedWriter buffered = new BufferedWriter(fileW);
			buffered.write("[ \n");
			buffered.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		Timer timer = new Timer();
		
		TimerTask timerT = new TimerTask() {
			
			public void run() {
				
				try {
				
					
				JSONArray temp = (JSONArray) obj.get("Weather Information");
				
				for (int i = 0; i<temp.size();i++)
				{
				        
				     JSONObject Temp = (JSONObject)temp.get(i);
				     DataTemp data= new DataTemp();
				      data.setTemp((double) Temp.get("Temp"));
					    data.setTemp_MIN((double) Temp.get("Temp_MIN"));
					    data.setTemp_MAX((double) Temp.get("Temp_MAX"));
					    data.setFeels_like((double) Temp.get("Feels_like"));
					    forecast.add(data);
				}
					    
				FileWriter fileW = new FileWriter(file, true);
				BufferedWriter buffered2 = new BufferedWriter(fileW);
				buffered2.write(temp.toJSONString());
				buffered2.write("\n");
				buffered2.close();
				
				if(forecast.size()>10) {
					timer.cancel();
					try {
						fileW = new FileWriter(file, true);
						BufferedWriter buffered3 = new BufferedWriter(fileW);
						buffered3.write("]");
						buffered3.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		};	
		
		timer.schedule(timerT, 0, 5000);
		
		
		
		return forecast;
	}
	
	/**
	 * metodo che prende i dati riguardanti le statistiche e li utilizza per
	 * creare un JSONObject
	 * @Override è un'annotazione utilizzata per indicare la sovrascrizione di 
	 * un metodo che deriva da una superclasse o da un'interfaccia
	 */
	public JSONObject Statits(String route) {
		
		StatsTemp StatsT = new StatsTemp();
		JSONObject statsT = new JSONObject(); 
		
		statsT.put("Temp_MAX", StatsT.getTempMax(route));
		statsT.put("Temp_MIN",StatsT.getTempMin(route));
		statsT.put("Average", StatsT.getAverage(route));
		statsT.put("Variance", StatsT.getVariance(route));
		
		/*StatsFeelsLike StatsFL = new StatsFeelsLike();
		JSONObject statsFL = new JSONObject();
		
		statsFL.put("Temp_MAX",  StatsFL.getTempMax(obj));
		statsFL.put("Temp_MIN",StatsFL.getTempMin(obj));
		statsFL.put("Average", StatsFL.getAverage(obj));
		statsFL.put("Variance", StatsFL.getVariance(obj));*/
		
		JSONObject sts = new JSONObject();
	    sts.put("Temperature statistics", statsT);      
		/*sts.put("Feels like statistics:", statsFL);*/
		
		return sts;
	}
}
