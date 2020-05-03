package com.example.AirlineReservationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.AirlineReservation.AirlineReservationApp;
import com.example.AirlineReservation.model.Flights;
import com.example.AirlineReservation.model.Users;
import com.example.AirlineReservation.service.AirlineServiceImpl;


@SpringBootTest
public class AirlineReservationAppTest {
	
	

	@Test
	public void contextLoads() {
	}
	
	private static Logger logger = LogManager.getLogger(AirlineReservationApp.class);
	
	@Autowired 
	AirlineServiceImpl airlineService;
	
	@BeforeAll
	static void setupBeforeClass() {
		logger.info("Setupclass called");
	}

	@BeforeEach
	void setup() {
		logger.info("Test Case Started");
		System.out.println("Test Case Started");	
	}
	
	@AfterEach()
	void tearDown() {
		logger.info("Test Case Over");
		System.out.print("Test Case Over");
	}
	
	@Test
	@DisplayName("User Logged In successfully")
	@Rollback(true)
	public void loginTest() {
			String methodn="login()";
			logger.info(methodn+" called");
			
			Integer value=airlineService.validateUser("amit","amit1");
			assertNotEquals(0,value);
	}
	
	
	@Test
	@DisplayName("Logged In Successfully")
	@Rollback(true)
	public void loginTest2() {
			String methodn="login()";
			logger.info(methodn+" called");
			
			Integer value=airlineService.validateUser("Ram","asd");
			assertNotEquals(2,value);
	}
	
	@Test
	@DisplayName("Booking Successfully")
	@Rollback(true)
	public void bookFlight() {
			
			assertNotEquals(true,airlineService.bookFlight(2,1));
	}
	
	
	@Test
	@DisplayName("User registration Successfully")
	@Rollback(true)
	public void createUser() {
			
			Users user=new Users();
			user.setAge(25);
			user.setUserId(20);;
			user.setName("amar");
			user.setUsername("amar");
			user.setPassword("amar1");
			
			boolean status =airlineService.registerUser(user);
			System.out.println("Users  "+status);
			assertEquals(true,status);
				
	}
	
	@Test
	@DisplayName("Cancellation Successfull")
	@Rollback(true)
	public void cancelFlight() {
			
			assertNotEquals(true,airlineService.cancelFlight(1257));
	}
	
	@Test
	@DisplayName("Search Flight")
	@Rollback(true)
	public void searchFlight() {
			
		List <Flights> flights=airlineService.searchFlight("PUNE","MUMBAI");
		assertNotNull(flights);
			
			
	}
	
	
	
	
}
