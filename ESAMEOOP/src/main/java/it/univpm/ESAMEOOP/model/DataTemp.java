package it.univpm.ESAMEOOP.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;


/**
 * Classe che descrive il modello dei dati riguaradanti le temperature 
 * @Component è un'annotazione che consente a Spring di rilevare automaticamente la classe
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
@Component
public class DataTemp {
	
	/**
	 * orario dell'osservazione in formato UNIX
	 */
	 private long date_UNIX;
	 /**
	  * orario dell'osservazione
	  */
	 private String date;
	 /**
	  * temperatura osservata
	  */
	 private double temp;
	 /**
	  * temperatura percepita
	  */
	 private double feels_like;
	 /**
	  * temperatura minima osservata
	  */
	 private double temp_MIN;
	 /**
	  * temperatura massima osservata
	  */
	 private double temp_MAX;
	 String weather;
	 String weather_description;
	 
	/** 
	 * @return date in formato UNIX
	 */
	public long getDate_UNIX() {
		return date_UNIX;
	}
	
	/**
	 * @param date_UNIX
	 * @return date in formato UNIX
	 */
	public long setDate_UNIX(long date_UNIX) {
		return this.date_UNIX = date_UNIX;
	}
	
	/**
	 * @return date
	 */
	public String getDate() {
	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(date_UNIX*1000);
	    return this.date=dateFormat.format(cal.getTime());
	}
	
	/**
	 * @param date
	 * @return date 
	 */
	public String setDate(String date) {
		return this.date=date;

	}
	
	/**
	 * @return temperature
	 */
	public double getTemp() {
		return temp;
	}
	
	/**
	 * @param temp The temperature to set
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	/**
	 * @return perceived temperature
	 */
	public double getFeels_like() {
		return feels_like;
	}
	
	/**
	 * @param feels_like The perceived temperature to set
	 */
	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}
	
	/**
	 * @return minimum temperature
	 */
	public double getTemp_MIN() {
		return temp_MIN;
	}
	
	/**
	 * @param temp_MIN The minimum temperature to set
	 */
	public void setTemp_MIN(double temp_MIN) {
		this.temp_MIN = temp_MIN;
	}
	
	/**
	 * @return maximum temperature
	 */
	public double getTemp_MAX() {
		return temp_MAX;
	}
	
	/**
	 * @param temp_MAX The maximum temperature to set
	 */
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
