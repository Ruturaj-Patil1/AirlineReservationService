package com.example.AirlineReservation.service;

import java.util.List;

import com.example.AirlineReservation.model.Flights;
import com.example.AirlineReservation.model.Tickets;
import com.example.AirlineReservation.model.Users;



public interface iAirlineSystem {

Integer bookFlight(int flightid,Integer userid);
	
	boolean cancelFlight(Integer bookid);
	
	boolean registerUser(Users users);
	
	Integer validateUser(String uname, String password);
	
	List<Flights> searchFlight(String source,String dest);
	
	List<Tickets> viewTickets(int userid);
}
