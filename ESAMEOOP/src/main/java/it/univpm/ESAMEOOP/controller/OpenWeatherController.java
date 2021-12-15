package it.univpm.ESAMEOOP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OpenWeatherService;

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
		return new ResponseEntity <> (openweatherservice.getDataWeather(3183087),HttpStatus.OK);
	}
	
	
			
	

}
