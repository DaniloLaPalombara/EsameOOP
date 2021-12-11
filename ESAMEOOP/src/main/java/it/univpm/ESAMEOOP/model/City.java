package it.univpm.ESAMEOOP.model;

public class City {
	
	private long id;
	private String country;
	private String name;
	private double temp;
	private double feels_like;
	private double temp_max;
	private double temp_min;
	private double lat;
	private double lon;
	//TODO eliminare parametri ed aggiungere vettore
	
	
	public City (long id, String country, String name, double temp, double flike, double tmax, double tmin,double lat,double lon) {
		this.id = id;
		this.country = country;
		this.name = name;
		this.temp = temp;
		this.feels_like = flike;
		this.temp_max = tmax;
		this.temp_min = tmin;	
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


	public double getTemp_max() {
		return temp_max;
	}


	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}


	public double getTemp_min() {
		return temp_min;
	}


	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
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
	

	
}
