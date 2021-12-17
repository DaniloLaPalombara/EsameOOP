package it.univpm.ESAMEOOP.service;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.*;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataWeather;

@Service
public class OpenWeatherService implements OpenWeatherInterface {
	
	private String Apikey="1df4cb04102d63e8af8fa80502fe09ae";
	private String URLCurrent="http://api.openweathermap.org/data/2.5/weather?id=";
	//private String URLHistory="http://history.openweathermap.org/data/2.5/history/city?id=";

	
	@Override
	public JSONObject getDataWeather(long id)
	{
		
		JSONObject fullInformation=new JSONObject();
		try {
			URLConnection openConnection = new URL(URLCurrent+id+"&appid="+Apikey).openConnection();
			InputStream in = openConnection.getInputStream();
			String data= " ";
			String line=" ";
	try {
			InputStreamReader reader= new InputStreamReader(in);
			BufferedReader buf = new BufferedReader(reader);
			
			while((line=buf.readLine())!= null)
			{
				data+=line;
			}
	   }
			finally {
			in.close();
			}
			

	fullInformation = (JSONObject) JSONValue.parseWithException(data);
			} catch (ParseException | IOException e) {

				e.printStackTrace();
			}
          catch (Exception e) {

			e.printStackTrace();
		}
		
		return fullInformation;
		

				
				
	}	
	@Override
	public City setDataWeather(JSONObject fullInformation)
	{
		DataWeather data = new DataWeather();
		City city= new City();
		JSONObject cityData = (JSONObject) fullInformation;
		city.setId((long)cityData.get("id"));
		city.setName((String)cityData.get("name"));
	    data.setDate_UNIX((long)cityData.get("dt"));
		JSONObject country=(JSONObject)cityData.get("sys");
		city.setCountry((String)country.get("country"));		
		JSONObject cord = (JSONObject)cityData.get("coord");
		//city.setLon((float)cord.get("lon"));
		//city.setLat((float)cord.get("lat"));

		
		JSONObject list = (JSONObject)cityData.get("main");
		Vector <DataWeather> forecast=new Vector<DataWeather>();
		data.setFeels_like((double)list.get("feels_like"));
		data.setTemp((double)list.get("temp"));
		data.setTemp_MIN((double)list.get("temp_min"));
		data.setTemp_MAX((double)list.get("temp_max"));
		/*for(int i = 0; i <list.size();i++)
		{
			JSONObject List = (JSONObject)list.get(i);*/
		JSONArray weather=(JSONArray)cityData.get("weather");
		for(int i = 0; i <weather.size();i++)
		{
			JSONObject List =(JSONObject)weather.get(i);
			data.setWeather((String)List.get("main"));
			data.setWeather_description((String)List.get("description"));
			
		}



			forecast.add(data);	
			
	
		//}
		
		city.setDataweather(forecast);
		return city;
	}

	@Override
	public JSONObject createJSON(City city) {
		JSONObject out = new JSONObject();
		out.put("City", city.getName());
		out.put("Id:", city.getId());
		out.put("Lat:", city.getLat());
		out.put("Lon", city.getLon());
		out.put("Country", city.getCountry());
		
		JSONArray weather = new JSONArray();
		for(DataWeather data:city.getDataweather())
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
		obj.put("Weather Information:", weather);
		
		return obj;

	}

	@Override
	public void saveFile(JSONObject obj) {
		// TODO Auto-generated method stub
		
	}	
		
}