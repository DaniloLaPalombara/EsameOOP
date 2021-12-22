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
 *
 * @Service è
 * 
 * @author danil
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


	@Override
	public JSONObject getDataWeather(long id)
	{
		
		JSONObject fullInformation=new JSONObject();
		try {
			URLConnection openConnection = new URL(URLHistory+id+"&type="+getType()+"&start="+getStart()+"&end="+getStop()+"&appid="+Apikey).openConnection();
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
		//DataTemp data = new DataTemp();
		City city=new City();
		JSONObject info=(JSONObject)fullInformation;
		city.setId((long)info.get("city_id"));
		
		JSONArray list = (JSONArray)info.get("list");
		Vector <DataTemp> forecast = new Vector<>();
		
		for(int i = 0; i < list.size();i++)
		{
			
			
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
	
	@Override
	public JSONObject createJSON(City city) 
	{
		JSONObject out = new JSONObject();
		out.put("Id:", city.getId());
		
		JSONArray weather = new JSONArray();
		for(DataTemp data:city.getDataTemp())
		{
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
	
	public JSONObject statist(JSONObject obj)
	{
		Stats Stats=new Stats();
		JSONObject stats = new JSONObject(); 
		stats.put("Temp_MAX",  Stats.getTempMax(obj));
		stats.put("Temp_MIN",Stats.getTempMin(obj));
		stats.put("Average", Stats.getAverage(obj));
		return stats;
		
	}

}
