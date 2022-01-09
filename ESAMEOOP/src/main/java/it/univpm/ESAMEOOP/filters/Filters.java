package it.univpm.ESAMEOOP.filters;

/**
 * Classe che fornisce gli elementi utili per
 * il filtraggio delle statistiche
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public class Filters{
	
	/**
	 * maxCalls Il numero massimo di chimamate da fare
	 * per il filtraggio delle statistiche
	 */
	protected int maxCalls;
	
	/**
	 * interval L'intervallo temporale tra una chiamata e l'altra
	 */
	protected int interval = 3600000;
	
	/**
	 * route Il percorso sul quale il file viene salvato in locale
	 */
	protected String route;
	
	public int getMaxCalls() {
		return maxCalls;
	}
	
	public void setMaxCalls(int maxCalls) {
		this.maxCalls = maxCalls;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}
}
