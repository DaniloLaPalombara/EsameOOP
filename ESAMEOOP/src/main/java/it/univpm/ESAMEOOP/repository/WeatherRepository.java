package it.univpm.ESAMEOOP.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.univpm.ESAMEOOP.model.City;


public interface WeatherRepository extends CrudRepository<City, long> {
	
	List<City> FindById (long id);
	
	@Query(value = "SELECT * FROM Meteo WHERE ID = :id AND EPOCH >= :start AND EPOCH <= :stop", nativeQuery = true)
	List<City> GetValue(@Param("id") long id, @Param("start") Long start, @Param("stop") Long stop);
	
	City savedCity = WeatherRepository.save(city); //TODO dove va?
}
