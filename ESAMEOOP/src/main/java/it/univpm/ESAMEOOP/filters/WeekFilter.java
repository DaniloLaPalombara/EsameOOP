package it.univpm.ESAMEOOP.filters;

import org.springframework.stereotype.Service;

@Service
public class WeekFilter extends Filters {
	
	public WeekFilter ()
	{
		super();
		this.maxCalls = 24*7;
		this.route=  System.getProperty("user.dir") + "/src/main/resources/" + "WeekStatistics";
		
	}


}
