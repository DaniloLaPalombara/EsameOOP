package it.univpm.ESAMEOOP.service;

import org.json.simple.JSONObject;

import it.univpm.ESAMEOOP.errors.DivisionByZeroException;

/**
 * Interfaccia che racchiude i metodi per il calcolo delle statistiche
 * effettuate sulle temperature attuali e percepite
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public interface StatsInterface {
	
	public abstract double getTempMax (String route);
	public abstract double getTempMin (String route);
	public abstract double getAverage (String route) throws DivisionByZeroException;
	public abstract double getVariance (String route) throws DivisionByZeroException;
}
