package it.univpm.ESAMEOOP.model;

import java.util.Vector;


import org.springframework.stereotype.Component;


/**
 * 
 * Classe che descrive il modello dei dati riguardanti la città
 * @Component è un'annotazione che consente a Spring di rilevare automaticamente la classe
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
@Component
public class City {
	/**
	 * Id della città
	 */
	protected long id;
	/**
	 * Paese in cui si trova la città
	 */
	private String country;
	/**
	 * Nome della città
	 */
	private String name;
	/**
	 * distanza angolare dall'equatore della città
	 */
	private double lat;
	/**
	 * distanza angolare dal Meridiano della città
	 */
	private double lon;
	/**
	 * vettore che contiene gli elementi riguaradanti le temperature
	 */
	private Vector<DataTemp> DataTemp = new Vector<>();
	
	
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

	public Vector<DataTemp> getDataTemp() {
		return DataTemp;
	}

	public void setDataTemp(Vector<DataTemp> DataTemp) {
		this.DataTemp = DataTemp;
	}
}
