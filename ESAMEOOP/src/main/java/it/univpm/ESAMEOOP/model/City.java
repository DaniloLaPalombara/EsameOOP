package it.univpm.ESAMEOOP.model;

import java.util.Vector;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity

@Table(name = "Weather")
public class City {
	
	@Id
	@GeneretedValue(strategy = GenerationType.AUTO);
	
	private long id;
	private String country;
	private String name;
	private double lat;
	private double lon;
	private long time;
	
	
	public City (long id, String country, String name,double lat,double lon, long time) {
		this.id = id;
		this.country = country;
		this.name = name;
		this.lat=lat;
		this.lon=lon;
		this.time=time;
	}

	public double getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public long getTime(long time) {
		return time;
	}

	/*public Vector<DataWeather> getDataweather() {
		return dataweather;
	}

	public void setDataweather(Vector<DataWeather> dataweather) {
		this.dataweather = dataweather;
	}
	*/ //TODO Servono davvero?
}
