package it.univpm.ESAMEOOP.service;

import java.util.*;

public class Stats {
	
	private Double tmax;
	private Double tmin;
	private Integer num;
	private Double counter;
	private Vector<Double> values;
	
	public Stats() {
		this.tmax = -Double.MAX_VALUE;
		this.tmin = Double.MAX_VALUE;
		this.num = 0;
		this.counter = 0.0;
		this.values = new Vector<Double>();
	}	
		
		
	public void addValue(Double value) {
		this.num++;
		this.counter += counter;
		
		if(value>this.tmax)
			this.tmax = value;
		
		if(value<this.tmin)
			this.tmin = value;
		
		this.values.add(value);
	}

	public Double getTmax() {
		
		if(tmax==-Double.MAX_VALUE)
			// TODO gestione errore
		else{
			return tmax;
		}	
	}
			
	public Double getTmin() {
		
		if(tmin==Double.MAX_VALUE)
			//TODO gestione errore
		else{
			return tmin;
		}	
	}	
	
	public Double getAverage() {
		
		if(tmax==-Double.MAX_VALUE)
			//TODO gestire errore
		else{
			return counter/num;
		}	
	}
	
					
	public Double getVariance() {
		
		if(tmax==-Double.MAX_VALUE)
			//TODO gestione errore
		else{
			Double average = this.getAverage();
			Double temp = 0.0;
			
			for (Double value : values) {
				temp += Math.pow((value-average), 2);
			}
			return temp/this.num;
		}
	}
}
