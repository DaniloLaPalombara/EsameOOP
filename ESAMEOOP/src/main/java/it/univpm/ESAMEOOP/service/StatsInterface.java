package it.univpm.ESAMEOOP.service;

import org.json.simple.JSONObject;

public interface StatsInterface {
	
	public abstract double getTempMax (JSONObject obj);
	public abstract double getTempMin (JSONObject obj);
	public abstract double getAverage (JSONObject obj);
	public abstract double getVariance (JSONObject obj);

}
