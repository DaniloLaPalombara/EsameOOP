package it.univpm.ESAMEOOP.model;

import java.util.Vector;

public class City {
	
	protected long id;
	private String country;
	private String name;
	private double lat;
	private double lon;
	private Vector<DataWeather> forecast;
	

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

	public Vector<DataWeather> getForecast() {
		return forecast;
	}

	public void setForecast(Vector<DataWeather> forecast) {
		this.forecast = forecast;
	}
}
