package it.univpm.ESAMEOOP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.service.TempService;
import it.univpm.ESAMEOOP.service.TempServiceHistory;


@RestController
public class TempController {
	
	@Autowired
	TempService openweatherservice;
	@Autowired
	TempServiceHistory openweatherservicehistory;
	

	@RequestMapping(value ="/prova")
	public City getString(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop)
	{
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
		return openweatherservicehistory.setDataWeather(openweatherservicehistory.getDataWeather(3183087)) ;
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
	
	@RequestMapping(value = "/default3")
	public ResponseEntity <Object> getforecast(@RequestParam(name="type",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="end",defaultValue="1628796785")Integer stop,@RequestParam(name="id",defaultValue="3183087")Integer id)
	{
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
		return new ResponseEntity<>(openweatherservicehistory.createJSON(openweatherservicehistory.setDataWeather(openweatherservicehistory.getDataWeather(id))),HttpStatus.OK);
	}
}
