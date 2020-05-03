package com.example.AirlineReservation.dao;

import java.util.List;

import com.example.AirlineReservation.model.Flights;
import com.example.AirlineReservation.model.Tickets;
import com.example.AirlineReservation.model.Users;



public interface iAirlineDAO {

boolean cancelFlight(Tickets bd);
	
	boolean registerUser(Users users);
	
	Integer validateUser(String uname, String password);
	
	List<Flights> searchFlight(String source,String dest);
	
	List<Tickets> viewTickets(int userid);
}
