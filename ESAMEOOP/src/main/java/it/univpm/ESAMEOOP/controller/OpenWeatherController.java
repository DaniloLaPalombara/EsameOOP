package it.univpm.ESAMEOOP.controller;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.ESAMEOOP.service.OpenWeatherService;


@RestController
public class OpenWeatherController {

	@Autowired
	OpenWeatherService openweatherservice;
	
	@RequestMapping(value = "/default")
	public ResponseEntity <Object> getforecast()
	{
		return new ResponseEntity<>(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(3183087))),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/default1")
	public ResponseEntity <Object> getforecas()
	{
		return new ResponseEntity<>(openweatherservice.getDataWeather(3183087),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/CurrentWeather")
	public ResponseEntity <Object> getforecastHistory(@RequestParam(name="id",defaultValue="3183087")Integer id)
	{
		return new ResponseEntity<>(openweatherservice.createJSON(openweatherservice.setDataWeather(openweatherservice.getDataWeather(id))),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/default1")
	public ResponseEntity <Object> getforecas(@RequestParam(name="interval",defaultValue="hour") String type,@RequestParam(name="start",defaultValue="1628776785")Integer start
			,@RequestParam(name="stop",defaultValue="1628778785")Integer stop)
	{
		openweatherservicehistory.setStart(start);
		openweatherservicehistory.setStop(stop);
		openweatherservicehistory.setType(type);
		return new ResponseEntity<>(openweatherservicehistory.getDataWeather(3183087),HttpStatus.OK);
	}
}
