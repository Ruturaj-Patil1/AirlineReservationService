package com.example.AirlineReservation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.AirlineReservation.AirlineReservationApp;
import com.example.AirlineReservation.model.Flights;
import com.example.AirlineReservation.model.Tickets;
import com.example.AirlineReservation.model.Users;


@Repository
public class AirlineDAOImpl implements iAirlineDAO{
	
	
	
	@Autowired
	private EntityManager entityManager;
	
	
	public void addNewFlight(Flights fd)
	{

		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(fd);
	}
	

	public void updateFlight(Flights fd) {

		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(fd);
		
		
	}

	public Tickets getBookingDetails(Integer bookindId)
	{
		return entityManager.find(Tickets.class, bookindId);
	}
	
	

	@Override
	public boolean registerUser(Users users) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	public boolean addBooking(Tickets bd)
	{
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(bd);
		return true;
	}

	@Override
	public Integer validateUser(String uname, String password) {
		Logger logger = LogManager.getLogger(AirlineReservationApp.class);
		try {
			String command = "select ud.userId from Users ud where ud.username=:uName and ud.password=:pass";
			TypedQuery<Integer> query = entityManager.createQuery(command,Integer.class);
			query.setParameter("uName", uname);
			query.setParameter("pass", password);
			Integer usid=0;
			
			usid = query.getSingleResult();
		
			
			if(usid>0) {
				logger.info("Username verified {}",uname);
				return usid;}
			else {
				return 0;}
			}
			catch (NoResultException e) {
				return 0;
			}	
	}

	public boolean createAccount(Users ud) {
		Logger logger = LogManager.getLogger(AirlineReservationApp.class);
		
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(ud);
		logger.info("New User Registered");
		return true;
		
	}
	
	@Transactional
	@Override
	public  List<Flights> searchFlight(String source, String dest)  {
		Logger logger = LogManager.getLogger(AirlineReservationApp.class);
		try {
			String qStr = "SELECT f FROM Flights f WHERE f.source=:ps and f.destination=:pd";
			TypedQuery<Flights> query2 = entityManager.createQuery(qStr, Flights.class);
			query2.setParameter("ps", source);
			query2.setParameter("pd", dest);
			
			List<Flights> flights = query2.getResultList();
			logger.info("Flights Available: {}",flights);
			return flights;
			}
			catch (NoResultException e) {
				return null;
			}
	}
	
	public Flights getFlightById(int flightId) {
		
		String qStr = "SELECT f FROM Flights f WHERE f.flightId=:fId1";
		TypedQuery<Flights> query4 = entityManager.createQuery(qStr, Flights.class);
		query4.setParameter("fId1", flightId);
		Flights flight = query4.getSingleResult();

		return flight;

	}


	@Override
	public boolean cancelFlight(Tickets bd) {

		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(bd);
				
		cs.delete(bd);
		
		return true;
	}


	@Override
	public List<Tickets> viewTickets(int userid) {
		Logger logger = LogManager.getLogger(AirlineReservationApp.class);
		String qStr = "SELECT t FROM Tickets t WHERE userId=:uId1";
		TypedQuery<Tickets> query = entityManager.createQuery(qStr, Tickets.class);
		query.setParameter("uId1", userid);
		List<Tickets> tickets = query.getResultList();
		logger.info("Tickets booked by userid:{} are {}",userid,tickets);
		return tickets;
	}
}
