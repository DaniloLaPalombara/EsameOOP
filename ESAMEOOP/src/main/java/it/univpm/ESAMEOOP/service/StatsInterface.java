package it.univpm.ESAMEOOP.service;

import org.json.simple.JSONObject;

/**
 * Interfaccia che racchiude i metodi per il calcolo delle statistiche
 * effettuate sulle temperature attuali e percepite
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public interface StatsInterface {
	
	public abstract double getTempMax (JSONObject obj);
	public abstract double getTempMin (JSONObject obj);
	public abstract double getAverage (JSONObject obj);
	public abstract double getVariance (JSONObject obj);
}
