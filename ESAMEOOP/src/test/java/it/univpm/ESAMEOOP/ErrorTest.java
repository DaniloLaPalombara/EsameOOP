package it.univpm.ESAMEOOP;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.ESAMEOOP.errors.JSONObjectNullException;
import it.univpm.ESAMEOOP.errors.ObjectEmptyException;
import it.univpm.ESAMEOOP.model.City;
import it.univpm.ESAMEOOP.service.TempService;
import it.univpm.ESAMEOOP.service.TempServiceHistory;

/**
 * Classe di test che serve per controllare il giusto funzionamneto
 * di una o più parti del programma
 * @author daniloLaPalombara&nicolòIanni
 *
 */
public class ErrorTest {
	
	TempService tempservice = new TempService();
	TempServiceHistory tempservicehistory = new TempServiceHistory();
	private JSONObject fullInformation;
	private JSONObject fullInformationH;
	private City city;
	private City cityH;
	private JSONObject obj;
	private JSONObject objH;

	/**
	 * @BeforeEach è un'annotazione che indica quale metodo
	 *            deve essere svolti prima del test
	 */
	@BeforeEach
	void setUp1() throws Exception {
		
		fullInformation = tempservice.GetData(3183087);
		fullInformationH = tempservicehistory.GetData(3183087);	
	}
	
	/**
	 * @AfterEach è un'annotazione che indica quale metodo
	 *           deve essere svolto dopo il test 
	 * 
	 */
	@AfterEach
	void tearDown1() throws Exception{}
	
	/**
	 * @Test è un'annotazione che indica qual è il metodo da testare
	 * @DisplayName è un'annotazione che definisce un nome
	 *             personalizzato per una classe di test
	 */
	@Test
	@DisplayName("Test 1 : Il JSONObject è nullo")
	void Test1() throws JSONObjectNullException {
		
		Assertions.assertNotNull(fullInformation);
		Assertions.assertNotNull(fullInformationH);
	}
	
	@BeforeEach
	void setUp2() throws Exception {
		
		city = tempservice.setData(fullInformation);
		cityH = tempservicehistory.setData(fullInformationH);	
	}
	
	@AfterEach
	void tearDown2() throws Exception{}
	
	@Test
	@DisplayName("Test 2 : L'oggetto city è vuoto")
	void Test2() throws ObjectEmptyException {
		Assertions.assertNotNull(city);
		Assertions.assertNotNull(cityH);
	}
	
	@BeforeEach
	void setUp3() throws Exception {
		obj = tempservice.createJSON(city);
		objH = tempservicehistory.createJSON(cityH);	
	}
	
	@AfterEach
	void tearDown3() throws Exception{}
	
	@Test
	@DisplayName("Test 3 : Il JSONObject è nullo")
	void Test3() throws JSONObjectNullException {
		
		Assertions.assertNotNull(obj);
		Assertions.assertNotNull(objH);
	}
}
