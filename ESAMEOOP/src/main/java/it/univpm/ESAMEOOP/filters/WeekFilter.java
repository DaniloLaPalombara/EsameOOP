package it.univpm.ESAMEOOP.filters;

import org.springframework.stereotype.Service;

/**
 * Classe derivata che si occupa di filtrare le
 * statistiche settimanalmente
 * @Service è un'annotazione che serve per contrassegnare la classe fornitore 
 * di servizi
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
@Service
public class WeekFilter extends Filters {
	
	public WeekFilter () {
		
		super();
		this.maxCalls = 24*7;
		this.route=  System.getProperty("user.dir") + "/src/main/resources/" + "WeekStatistics";	
	}
}
