package it.univpm.ESAMEOOP.model;

public class City {
	
	private double id;
	private String country;
	private String name;
	private double temp;
	private double feels_like;
	private double temp_max;
	private double temp_min;
	
	public City (double id, String country, String name, double temp, double flike, double tmax, double tmin) {
		this.id = id;
		this.country = country;
		this.name = name;
		this.temp = temp;
		this.feels_like = flike;
		this.temp_max = tmax;
		this.temp_min = tmin;	
	}

}
