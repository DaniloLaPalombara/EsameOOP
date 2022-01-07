package it.univpm.ESAMEOOP.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ESAMEOOP.errors.DivisionByZeroException;
import it.univpm.ESAMEOOP.errors.JSONObjectNullException;
import it.univpm.ESAMEOOP.filters.DailyFilter;
import it.univpm.ESAMEOOP.filters.WeekFilter;
import it.univpm.ESAMEOOP.service.StatsTemp;
import it.univpm.ESAMEOOP.service.TempCompare;
import it.univpm.ESAMEOOP.service.TempService;
import it.univpm.ESAMEOOP.service.TempServiceHistory;



@RestController
public class TempController {
	
	@Autowired
	TempService openweatherservice;
	@Autowired
	TempServiceHistory openweatherservicehistory;
	@Autowired
	StatsTemp stats;
	@Autowired
	DailyFilter dailyfilter;
	@Autowired
	WeekFilter weekfilter;
	@Autowired
	TempCompare compare;

	@RequestMapping(value = "/CurrentTemp")
	public ResponseEntity <Object> getforecast(@RequestParam(name="id",defaultValue="3183087")Integer id) throws JSONObjectNullException {
		
		return new ResponseEntity<>(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/HistoryTemp")
	public ResponseEntity <Object> getforecas(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop, @RequestParam(name="id", defaultValue="3183087") Integer id) throws JSONObjectNullException {
		
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
		return new ResponseEntity<>(openweatherservicehistory.createJSON(openweatherservicehistory.setDataWeather(openweatherservicehistory.getDataWeather(id))), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/HourlySaving&Statistics")
	public ResponseEntity <Object> getHourlySaveStatistics(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop,@RequestParam(name="id",defaultValue="3183087")Integer id) throws DivisionByZeroException, JSONObjectNullException {
		
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
		String route = openweatherservicehistory.getRoute();
		openweatherservicehistory.Saving(openweatherservicehistory.createJSON(openweatherservicehistory.setDataWeather(openweatherservicehistory.getDataWeather(id))),route );
		return new ResponseEntity<>(openweatherservicehistory.Statistics(route),HttpStatus.OK);
	}
	
	@RequestMapping(value = "HourlyStatistics")
	public ResponseEntity <Object> getHourlyStatistics(@RequestParam(name="id", defaultValue="3183087")Integer id) throws DivisionByZeroException {
		
		String route= openweatherservicehistory.getRoute();
		return new ResponseEntity<>(openweatherservice.Statistics(route), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/DailySaving&Statistics")
	public ResponseEntity<Object> getDailySaveStatistics(@RequestParam(name="id", defaultValue="3183087")Integer id) throws JSONObjectNullException, DivisionByZeroException {
		
		int i=0;
		String route= dailyfilter.getRoute();
		openweatherservice.Saving(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))), dailyfilter.getMaxCalls(), dailyfilter.getInterval(),route);	
	    while(openweatherservice.getCounter()!= dailyfilter.getMaxCalls()) {
	    	i++;
	    }
		return new ResponseEntity<>(openweatherservice.Statistics(route), HttpStatus.OK);				
	}
	
	@RequestMapping(value = "DailyStatistics")
	public ResponseEntity <Object> getDailyStatistics(@RequestParam(name="id", defaultValue="3183087")Integer id) throws DivisionByZeroException {
		
		String route= dailyfilter.getRoute();
		return new ResponseEntity<>(openweatherservice.Statistics(route), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/WeekSaving&Statistics")
	public ResponseEntity<Object> getWeekSaveStatistic(@RequestParam(name="id", defaultValue="3183087")Integer id) throws JSONObjectNullException, DivisionByZeroException {
		
		int i=0;
		String route= weekfilter.getRoute();
		openweatherservice.Saving(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))), weekfilter.getMaxCalls(), weekfilter.getInterval(),route);	
	    while(openweatherservice.getCounter()!= weekfilter.getMaxCalls()) {
	    	i++;
	    }
		return new ResponseEntity<>(openweatherservice.Statistics(route), HttpStatus.OK);				
	}
	
	@RequestMapping(value = "WeekStatistics")
	public ResponseEntity <Object> getWeekStatistic(@RequestParam(name="id", defaultValue="3183087")Integer id) throws DivisionByZeroException {
		
		String route= weekfilter.getRoute();
		
		return new ResponseEntity<>(openweatherservice.Statistics(route), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/TempCompare")
	public ResponseEntity<Object> getCompare(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop,@RequestParam(name="id",defaultValue="3183087")Integer id) throws JSONObjectNullException, DivisionByZeroException {
		
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
	    String route = compare.getRoute();
		openweatherservicehistory.Saving(openweatherservicehistory.createJSON(openweatherservicehistory.setDataWeather(openweatherservicehistory.getDataWeather(id))),route );
		
	    return new ResponseEntity<>(compare.Compare(compare.CompareCurrent(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))),openweatherservicehistory.Statistics(route)), HttpStatus.OK);
	}
}
