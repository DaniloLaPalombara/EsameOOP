package it.univpm.ESAMEOOP.controller;

import java.util.Vector;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ESAMEOOP.filters.DailyFilter;
import it.univpm.ESAMEOOP.filters.WeekFilter;
import it.univpm.ESAMEOOP.model.City;
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

	
	@RequestMapping(value = "/provaSave")
	public String hourlySaving(@RequestParam(name="id",defaultValue="3183087")Integer id) {
		openweatherservice.Saving(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))), id);
		return("Salvataggio iniziato");
	}

	@RequestMapping(value ="/Statistics")
	public ResponseEntity<Object> getStatistics(@RequestParam(name="id",defaultValue="3183087") Integer id)
	{
		//Vector data = openweatherservice.HourlySaving(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))), id);
		return new ResponseEntity<>(openweatherservice.Statistics(openweatherservice.getRoute()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/default1")
	public ResponseEntity <Object> getforecas(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop)
	{
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
		return new ResponseEntity<>(openweatherservicehistory.getDataWeather(3183087),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/CurrentWeather")
	public ResponseEntity <Object> getforecast(@RequestParam(name="id",defaultValue="3183087")Integer id)
	{
		return new ResponseEntity<>(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/HourlyStatistics")
	public ResponseEntity <Object> getforecast(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop,@RequestParam(name="id",defaultValue="3183087")Integer id)
	{
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
		String route = openweatherservicehistory.getRoute();
		openweatherservicehistory.Saving(openweatherservicehistory.createJSON(openweatherservicehistory.setDataWeather(openweatherservicehistory.getDataWeather(id))),route );
		return new ResponseEntity<>(openweatherservicehistory.Statistics(route),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/DailyStatistic")
	public ResponseEntity<Object> getDailystatistic(@RequestParam(name="id", defaultValue="3183087")Integer id)
	{
		
		int i=0;
		String route= dailyfilter.getRoute();
		openweatherservice.Saving(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))), dailyfilter.getMaxCalls(), dailyfilter.getInterval(),route);	
	    while(openweatherservice.getCounter()!= dailyfilter.getMaxCalls())
	    {
	    	i++;
	    }
		return new ResponseEntity<>(openweatherservice.Statistics(route), HttpStatus.OK);
	    
	    
					
	}
	
	@RequestMapping(value = "/WeekStatistic")
	public ResponseEntity<Object> getWeekstatistic(@RequestParam(name="id", defaultValue="3183087")Integer id)
	{
		
		int i=0;
		String route= weekfilter.getRoute();
		openweatherservice.Saving(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))), weekfilter.getMaxCalls(), weekfilter.getInterval(),route);	
	    while(openweatherservice.getCounter()!= weekfilter.getMaxCalls())
	    {
	    	i++;
	    }
		return new ResponseEntity<>(openweatherservice.Statistics(route), HttpStatus.OK);				
	}
	
	@RequestMapping(value = "/TempCompare")
	public ResponseEntity<Object> getCompare(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop,@RequestParam(name="id",defaultValue="3183087")Integer id) {
		
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
	    compare.CompareCurrent(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id)));
	    
	    return new ResponseEntity<>(

	}
}
