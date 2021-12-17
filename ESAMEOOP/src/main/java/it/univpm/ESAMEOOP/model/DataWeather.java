package it.univpm.ESAMEOOP.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DataWeather {
	
	 private long date_UNIX;
	 private String date;
	 private double temp;
	 private double feels_like;
	 private double temp_MIN;
	 private double temp_MAX;
	 String weather;
	 String weather_description;
	 
	 
	public long getDate_UNIX() {
		return date_UNIX;
	}
	
	public long setDate_UNIX(long date_UNIX) {
		return this.date_UNIX = date_UNIX;
	}
	
	public String getDate() {
		return date;
	}
	
	public String setDate(String date) {
		
	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(date_UNIX*1000);
	    return this.date=dateFormat.format(cal.getTime());
	}
	
	public double getTemp() {
		return temp;
	}
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public double getFeels_like() {
		return feels_like;
	}
	
	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}
	
	public double getTemp_MIN() {
		return temp_MIN;
	}
	
	public void setTemp_MIN(double temp_MIN) {
		this.temp_MIN = temp_MIN;
	}
	
	public double getTemp_MAX() {
		return temp_MAX;
	}
	
	public void setTemp_MAX(double temp_MAX) {
		this.temp_MAX = temp_MAX;
	}
	public String getWeather() {
		return weather;
	}
	
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	public String getWeather_description() {
		return weather_description;
	}
	
	public void setWeather_description(String weather_description) {
		this.weather_description = weather_description;
	}
}
