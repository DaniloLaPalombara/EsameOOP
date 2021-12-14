package it.univpm.ESAMEOOP.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.simple.*;
import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.model.DataWeather;

public class OpenWeatherService implements OpenWeatherInterface {
	
	
	private String Apikey="1df4cb04102d63e8af8fa80502fe09ae";
	private String URLCurrent="api.openweathermap.org/data/2.5/weather?id=";
	private String URLHistory="http://history.openweathermap.org/data/2.5/history/city?id=";
	City city;
	
	public JSONObject getDataWeather(long id)
	{
		// Costruisco l'URL
		URL URL1 = new URL(URLCurrent+id+"&appid="+Apikey);
		
		// Ottengo la connessione al servizio
		URLConnection Current=URL1.openConnection();
		
		// Preparo lo StringBuilder che accoglier� il JSON
		StringBuilder Data = new StringBuilder();
		
		// Eseguo la request e leggo il risultato
		InputStream in = Current.getInputStream();
		
		// Essendo che ricevo un JSON (quindi una stringa) posso usare in Reader
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		
		// Leggo finch� ci sono dati da leggere
		while((line = br.readLine()) !=null)
		{
			Data.append(line);
		}
		
		// Chiudo la connessione
		br.close();
		
		// Costruisco il JSON
		JSONObject DataWeather = new JSONObject(Data.toString());
		return DataWeather;
		
	}
	
	public City setDataWeather(JSONObject dataWeather)
	{
		//JSONParser parser = new JSONParses();
		JSONObject cityInformation = (JSONObject) dataWeather.get("city");
		city.setName((String)dataWeather.get("name"));
		city.setCountry((String)dataWeather.get("country"));
		city.setId((long)dataWeather.get("id"));
		city.setLon((double)dataWeather.get("Lon"));
		city.setLat((double)dataWeather.get("Lat"));
		
		JSONArray  list = (JSONArray)dataWeather.get("list");
		Vector <DataWeather> forecast=new Vector<DataWeather>();
		for(int i = 0; i <list.size();i++)
		{
			JSONObject List = (JSONObject)list.get(i);
			DataWeather data = new DataWeather();
			JSONObject weather = (JSONArray)(JSONObject)List.get("weather");
			JSONObject weather_description=(JSONObject)List.get("main");
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFile(JSONObject obj) {
		// TODO Auto-generated method stub
		
	}	
}
