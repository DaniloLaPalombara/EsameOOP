package it.univpm.ESAMEOOP.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.simple.*;
import org.json.simple.parser.ParseException;

import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataWeather;

public class OpenWeatherService implements OpenWeatherInterface {
	
	
	private String Apikey="1df4cb04102d63e8af8fa80502fe09ae";
	private String URLCurrent="api.openweathermap.org/data/2.5/weather?id=";
	private String URLHistory="http://history.openweathermap.org/data/2.5/history/city?id=";
	City city;
	
	@Override
	public JSONObject getDataWeather(long id)
	{
		
		JSONObject fullInformation=null;
		try {
			URLConnection openConnection = new URL(URLCurrent+id+"&appid="+Apikey).openConnection();
			InputStream in = openConnection.getInputStream();
			String data= "";
			String line="";
			InputStreamReader reader= new InputStreamReader(in);
			BufferedReader buf = new BufferedReader(reader);
			
			while((line=buf.readLine())!= null)
			{
				data+=line;
			}
			in.close();
			
			try {
				fullInformation = (JSONObject) JSONValue.parseWithException(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fullInformation;

				
				
	}	
	@Override
	public City setDataWeather(JSONObject fullInformation)
	{
		City city= new City();
		//JSONParser parser = new JSONParses();
		JSONObject cityInformation = (JSONObject)fullInformation.get("city");
		city.setName((String)cityInformation.get("name"));
		city.setCountry((String)cityInformation.get("country"));
		city.setId((long)cityInformation.get("id"));
		city.setLon((double)cityInformation.get("Lon"));
		city.setLat((double)cityInformation.get("Lat"));
		
		JSONArray list = (JSONArray)fullInformation.get("list");
		Vector <DataWeather> forecast=new Vector<DataWeather>();
		for(int i = 0; i <list.size();i++)
		{
			JSONObject List = (JSONObject)list.get(i);
			DataWeather data = new DataWeather();
			//JSONObject weather = (JSONObject)(JSONOArray)List.get("weather");//Controllare bene
			//JSONObject weather_description=(JSONObject)List.get("main");
			
			data.setDate((String)List.get("dt"));
			data.setFeels_like((double)List.get("feels_like"));
			data.setTemp((double)List.get("emp"));
			data.setTemp_MIN((double)List.get("temp_min"));
			data.setTemp_MAX((double)List.get("temp_max"));
			forecast.add(data);			
		}
		
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
	    obj.put("", out);      
		obj.put("Information:", weather);
		
		return obj;

	}

	@Override
	public void saveFile(JSONObject obj) {
		// TODO Auto-generated method stub
		
	}	
}
