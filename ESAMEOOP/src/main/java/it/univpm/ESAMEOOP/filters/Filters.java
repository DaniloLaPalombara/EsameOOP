package it.univpm.ESAMEOOP.filters;


public class Filters{
	protected int maxCalls;
	protected int interval = 5000;
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
