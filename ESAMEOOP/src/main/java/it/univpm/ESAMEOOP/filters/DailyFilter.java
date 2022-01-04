package it.univpm.ESAMEOOP.filters;

import org.springframework.stereotype.Service;

/**
 * Classe derivata che si occupa di filtrare le
 * statistiche giornalmente
 * @Service è un'annotazione che serve per contrassegnare la classe fornitore 
 * di servizi
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
@Service
public class DailyFilter extends Filters {
	
	public DailyFilter() {
		
		super();
		this.maxCalls = 24;
		this.route = System.getProperty("user.dir") + "/src/main/resources/" + "DailyStatistics";	
	}
}
