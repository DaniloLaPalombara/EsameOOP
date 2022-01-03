package it.univpm.ESAMEOOP.filters;

import org.springframework.stereotype.Service;

@Service
public class DailyFilter extends Filters {
	
	public DailyFilter ()
	{
		super();
		this.maxCalls = 24;
		this.route=  System.getProperty("user.dir") + "/src/main/resources/" + "DailyStatistics";
		
	}

}
