package it.univpm.ESAMEOOP.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import it.univpm.ESAMEOOP.errors.TimeSlotException;


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
	 long start;
	 long stop;
	 
	/** 
	 * @return date in formato UNIX
	 */
	/*public void getDate_UNIX(CharSequence start, CharSequence stop) throws TimeSlotException {
		
		
		if(start != null && stop != null ) {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneOffset.systemDefault());
			
			if((Instant.from(dtf.parse(start)).toEpochMilli()/1000) < (Instant.from(dtf.parse(stop)).toEpochMilli()/1000)) {
			
			this.start = Instant.from(dtf.parse(start)).toEpochMilli()/1000;
			this.stop = Instant.from(dtf.parse(stop)).toEpochMilli()/1000;
		    } else {
		    	
		    	throw new TimeSlotException("Error: invalid range");
		    }
		}
	}*/	
	
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
	
	public String setDate(String date) {
		return this.date=date;

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
