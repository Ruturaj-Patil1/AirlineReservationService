package com.example.AirlineReservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AirlineReservation.model.Flights;
import com.example.AirlineReservation.model.Tickets;
import com.example.AirlineReservation.model.Users;
import com.example.AirlineReservation.service.AirlineServiceImpl;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AirlineReservationController {

	@Autowired
	AirlineServiceImpl airlineService;
	
	@PostMapping("/addUser")
	public boolean createUser(@RequestBody Users users)
	{
		return airlineService.registerUser(users);
	}

	
	
	@GetMapping("/login/{uName}/{pass}")
	public Integer login(@PathVariable String uName,@PathVariable String pass) {
		return airlineService.validateUser(uName, pass);
	}
	
	@GetMapping("/searchFlight/{source}/{dest}")
	public List<Flights> searchFlight(@PathVariable String source,@PathVariable String dest)
	{
		return airlineService.searchFlight(source, dest);
	}
	
	@GetMapping("/all/{userid}")
	public List<Tickets> getBookings(@PathVariable Integer userid)
	{
		return airlineService.viewTickets(userid);
	}
	@GetMapping("/bookFlight/{flightId}/{userId}")
	public Integer bookFlight(@PathVariable Integer flightId,@PathVariable Integer userId)
	{
		return airlineService.bookFlight(flightId, userId);
	}
	
	@GetMapping("/cancelFlight/{bookingId}")
	public boolean cancelFlight(@PathVariable Integer bookingId)
	{
		return airlineService.cancelFlight(bookingId);
	}	
}
