package it.univpm.ESAMEOOP.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ESAMEOOP.errors.DivisionByZeroException;
import it.univpm.ESAMEOOP.errors.JSONObjectNullException;
import it.univpm.ESAMEOOP.errors.ObjectEmptyException;
import it.univpm.ESAMEOOP.filters.DailyFilter;
import it.univpm.ESAMEOOP.filters.WeekFilter;
import it.univpm.ESAMEOOP.service.StatsTemp;
import it.univpm.ESAMEOOP.service.TempCompare;
import it.univpm.ESAMEOOP.service.TempService;
import it.univpm.ESAMEOOP.service.TempServiceHistory;


/**
 * Controller con il quale chiamare le rotte e che elabora
 *  le richieste web degli utenti
 * @RestController è un'annotazione che aggiunge implicitamente @Controller
 * e @ResponseBody, la prima viene utilizzata per contrassegnare le classi
 * come Spring MVC Controller, la seconda indica che il tipo restituito deve
 * essere scritto direttamente nel corpo della richiesta HTTP
 * 
 * @author daniloLaPalombara&nicolòIanni
 *
 */
@RestController
public class TempController {
	
	/**
	 * @Autowired è un'annotazione utile nel guidare l'inezione
	 * delle dipendenze per mezzo di annotazioni
	 */
	@Autowired
	TempService tempservice;
	@Autowired
	TempServiceHistory tempservicehistory;
	@Autowired
	StatsTemp stats;
	@Autowired
	DailyFilter dailyfilter;
	@Autowired
	WeekFilter weekfilter;
	@Autowired
	TempCompare compare;

	/**
	 * Rotta che restituisce i dati riguaradanti la temperatura corrente di una città
	 * @throws ObjectEmptyException 
	 * @RequestMapping è un'annotazione che ha lo scopo di definire
	 * un'associazione tra un handler ed un gruppo URL
	 */
	@RequestMapping(value = "/CurrentTemp")
	public ResponseEntity <Object> getCurrent(@RequestParam(name="id",defaultValue="3183087")Integer id) throws JSONObjectNullException, ObjectEmptyException {
		
		return new ResponseEntity<>(tempservice.createJSON(tempservice.setDataWeather(tempservice.getDataWeather(id))),HttpStatus.OK);
	}
	
	/**
	 * Rotta che restituisce i dati riguaradanti lo storico delle temperature
	 * in una fascia oraria di una città
	 * @throws ObjectEmptyException 
	 */
	@RequestMapping(value = "/HistoryTemp")
	public ResponseEntity <Object> getHistory(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop, @RequestParam(name="id", defaultValue="3183087") Integer id) throws JSONObjectNullException, ObjectEmptyException {
		
		tempservicehistory.setStart(start);
		tempservicehistory.setStop(stop);
		tempservicehistory.setType(type);
		return new ResponseEntity<>(tempservicehistory.createJSON(tempservicehistory.setDataWeather(tempservicehistory.getDataWeather(id))), HttpStatus.OK);
	}
	
	/**
	 * Rotta che salva localmente e restituisce le statistiche sulle temperature attuali e
	 * percepite effettuate in una fascia oraria 
	 * @throws ObjectEmptyException 
	 */
	@RequestMapping(value = "/HourlySaving&Statistics")
	public ResponseEntity <Object> getHourlySaveStatistics(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop,@RequestParam(name="id",defaultValue="3183087")Integer id) throws DivisionByZeroException, JSONObjectNullException, ObjectEmptyException {
		
		tempservicehistory.setStart(start);
		tempservicehistory.setStop(stop);
		tempservicehistory.setType(type);
		String route = tempservicehistory.getRoute();
		tempservicehistory.Saving(tempservicehistory.createJSON(tempservicehistory.setDataWeather(tempservicehistory.getDataWeather(id))),route );
		return new ResponseEntity<>(tempservicehistory.Statistics(route),HttpStatus.OK);
	}
	
	/**
	 * Rotta che restituisce le statistiche sulle temperature attuali e
	 * percepite effettuate in una fascia oraria
	 */
	@RequestMapping(value = "HourlyStatistics")
	public ResponseEntity <Object> getHourlyStatistics(@RequestParam(name="id", defaultValue="3183087")Integer id) throws DivisionByZeroException {
		
		String route= tempservicehistory.getRoute();
		return new ResponseEntity<>(tempservice.Statistics(route), HttpStatus.OK);
	}
	
	/**
	 *Rotta che salva localmente e restituisce le statistiche sulle temperature attuali e
	 *percepite effettuate nell'arco di una giornata
	 * @throws ObjectEmptyException 
	 */
	@RequestMapping(value = "/DailySaving&Statistics")
	public ResponseEntity<Object> getDailySaveStatistics(@RequestParam(name="id", defaultValue="3183087")Integer id) throws JSONObjectNullException, DivisionByZeroException, ObjectEmptyException {
		
		int i=0;
		String route= dailyfilter.getRoute();
		tempservice.Saving(tempservice.createJSON(tempservice.setDataWeather(tempservice.getDataWeather(id))), dailyfilter.getMaxCalls(), dailyfilter.getInterval(),route);	
	    while(tempservice.getCounter()!= dailyfilter.getMaxCalls()) {
	    	i++;
	    }
		return new ResponseEntity<>(tempservice.Statistics(route), HttpStatus.OK);				
	}
	
	/**
	 * Rotta che restituisce le statistiche sulle temperature attuali e
	 * percepite effettuate nell'arco di una giornata
	 */
	@RequestMapping(value = "DailyStatistics")
	public ResponseEntity <Object> getDailyStatistics(@RequestParam(name="id", defaultValue="3183087")Integer id) throws DivisionByZeroException {
		
		String route= dailyfilter.getRoute();
		return new ResponseEntity<>(tempservice.Statistics(route), HttpStatus.OK);
	}
	
	/**
	 * Rotta che salva localmente e restituisce le statistiche sulle temperature attuali e
	 * percepite effettuate nell'arco di una settimana
	 * @throws ObjectEmptyException 
	 */
	@RequestMapping(value = "/WeekSaving&Statistics")
	public ResponseEntity<Object> getWeekSaveStatistic(@RequestParam(name="id", defaultValue="3183087")Integer id) throws JSONObjectNullException, DivisionByZeroException, ObjectEmptyException {
		
		int i=0;
		String route= weekfilter.getRoute();
		tempservice.Saving(tempservice.createJSON(tempservice.setDataWeather(tempservice.getDataWeather(id))), weekfilter.getMaxCalls(), weekfilter.getInterval(),route);	
	    while(tempservice.getCounter()!= weekfilter.getMaxCalls()) {
	    	i++;
	    }
		return new ResponseEntity<>(tempservice.Statistics(route), HttpStatus.OK);				
	}
	
	/**
	 *Rotta che restituisce le statistiche sulle temperature attuali e
	 *percepite effettuate nell'arco di una settimana
	 */
	@RequestMapping(value = "WeekStatistics")
	public ResponseEntity <Object> getWeekStatistic(@RequestParam(name="id", defaultValue="3183087")Integer id) throws DivisionByZeroException {
		
		String route= weekfilter.getRoute();
		
		return new ResponseEntity<>(tempservice.Statistics(route), HttpStatus.OK);
	}
	
	/**
	 * Rotta che restituisce le statistiche sulle temperature attuali e
	 * percepite effettuate in una fascia oraria, confrontate con i dati della temperatura attuale
	 * @throws ObjectEmptyException 
	 */
	@RequestMapping(value = "/TempCompare")
	public ResponseEntity<Object> getCompare(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop,@RequestParam(name="id",defaultValue="3183087")Integer id) throws JSONObjectNullException, DivisionByZeroException, ObjectEmptyException {
		
		tempservicehistory.setStart(start);
		tempservicehistory.setStop(stop);
		tempservicehistory.setType(type);
	    String route = compare.getRoute();
		tempservicehistory.Saving(tempservicehistory.createJSON(tempservicehistory.setDataWeather(tempservicehistory.getDataWeather(id))),route );
		
	    return new ResponseEntity<>(compare.Compare(compare.CompareCurrent(tempservice.setDataWeather(tempservice.getDataWeather(id))),tempservicehistory.Statistics(route)), HttpStatus.OK);
	}
}
