package it.univpm.ESAMEOOP.model;

import java.util.Vector;

public class City {
	
	private long id;
	private String country;
	private String name;
	private double lat;
	private double lon;
	private Vector <DataWeather> dataweather= new Vector <>();
	
	
	public City (long id, String country, String name,double lat,double lon) {
		this.id = id;
		this.country = country;
		this.name = name;
		this.lat=lat;
		this.lon=lon;
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

	public Vector<DataWeather> getDataweather() {
		return dataweather;
	}

	public void setDataweather(Vector<DataWeather> dataweather) {
		this.dataweather = dataweather;
	}
}
