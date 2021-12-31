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
	
	
	/**
	 * @return Id
	 */
	public double getId() {
		return id;
	}
	
    /**
     * @param id The id to set 
     */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country The country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Latitude
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat The latitude to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return Longitude
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @param lon The longitude to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * @return vector DataTemp
	 */
	public Vector<DataTemp> getDataTemp() {
		return DataTemp;
	}

	/**
	 * @param DataTemp The vector DataTemp to set
	 */
	public void setDataTemp(Vector<DataTemp> DataTemp) {
		this.DataTemp = DataTemp;
	}
}
