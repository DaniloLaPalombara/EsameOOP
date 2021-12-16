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
	private String URLCurrent="api.openweathermap.org/data/2.5/weather?id=";
	//private String URLHistory="http://history.openweathermap.org/data/2.5/history/city?id=";

	
	@Override
	public JSONObject getDataWeather(long id) {
		//JSONObject forecast = null;
		JSONObject forecast = new JSONObject();
		
		try {
			URLConnection openConnection = new URL(URLCurrent+id+"&appid="+Apikey).openConnection();
			InputStream in = openConnection.getInputStream();
			String data= "";
			String line= "";
	    try {
			InputStreamReader reader= new InputStreamReader(in);
			BufferedReader buf = new BufferedReader(reader);
			
			while((line=buf.readLine())!= null)
			{
				data+=line;
			}
	     } finally {
			in.close();
			}
	    
	        forecast = (JSONObject) JSONValue.parseWithException(data);
	    
		} catch (ParseException | IOException e) {
			e.printStackTrace();
	    }
          catch (Exception e) {
        	  e.printStackTrace();
          }	  
		
		return forecast;			
	}
	
	@Override
	public City setDataWeather(JSONObject obj)
	{
		City city= new City();
		Vector<DataWeather> forecastData = new Vector<DataWeather>();
		JSONObject cityInformation = (JSONObject)obj.get("city");
		JSONArray list = (JSONArray)obj.get("list");
		
		city.setName((String)cityInformation.get("name"));
		city.setCountry((String)cityInformation.get("country"));
		city.setId((long)cityInformation.get("id"));
		city.setLon((double)cityInformation.get("Lon"));
		city.setLat((double)cityInformation.get("Lat"));
		
		for(int i = 0; i <list.size();i++)
		{
			JSONObject listElement = (JSONObject)list.get(i);
			DataWeather data = new DataWeather();
			
			//JSONObject weather = (JSONObject)((JSONArray)List.get("weather")).get(i);
			//JSONObject weather_description=(JSONObject)List.get("main");
			
			JSONObject info = (JSONObject)((JSONArray)listElement.get("info")).get(i);
			//JSONObject main=(JSONObject)listElement.get("main");
			
			data.setDate((String)listElement.get("dt"));
			data.setFeels_like((double)listElement.get("feels_like"));
			data.setTemp((double)listElement.get("temp"));
			data.setTemp_MIN((double)listElement.get("temp_min"));
			data.setTemp_MAX((double)listElement.get("temp_max"));
			//data.setWeather((String)weather.get("main"));
			forecastData.add(data);	
		}
		
		city.setForecast(forecastData);
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
		
		JSONArray info = new JSONArray();
		
		for(DataWeather data:city.getForecast())
		{
			JSONObject forecast = new JSONObject();
			forecast.put("Date", data.getDate());
			forecast.put("Feels_like", data.getFeels_like());
			forecast.put("Temp", data.getTemp());
			forecast.put("Temp_MAX", data.getTemp_MAX());
			forecast.put("Temp_MIN", data.getTemp_MIN());
			//forecast.put("Weather:", data.());
			//WeatherData.put("Descriprion:", data.getWeather_description());

			info.add(forecast);
			
		}
		JSONObject obj = new JSONObject();
	    obj.put("", out);      
		obj.put("Information:", info);
		
		return obj;

	}

	/*@Override
	public void saveFile(JSONObject obj) {
		// TODO Auto-generated method stub*/
		
}