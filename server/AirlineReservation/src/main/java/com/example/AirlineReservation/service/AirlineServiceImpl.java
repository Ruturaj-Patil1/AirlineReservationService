package com.example.AirlineReservation.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AirlineReservation.AirlineReservationApp;
import com.example.AirlineReservation.dao.AirlineDAOImpl;
import com.example.AirlineReservation.model.Flights;
import com.example.AirlineReservation.model.Tickets;
import com.example.AirlineReservation.model.Users;



@Service
@Transactional
public class AirlineServiceImpl implements iAirlineSystem{
	@Autowired
	private AirlineDAOImpl daoObject;
	@Autowired
	private EntityManager entityManager;

	private static int bookingId;
	
	public AirlineServiceImpl() {
	}
	
	@Transactional
	@Override
	public boolean registerUser(Users users) {
		Integer userId = 1;
		String command = "select count(ud.userId) from Users ud";
		TypedQuery<Long> query = entityManager.createQuery(command, Long.class);
		long count = query.getSingleResult();
		if (count > 0) {
			command = "select max(ud.userId) from Users ud";
			TypedQuery<Integer> query2 = entityManager.createQuery(command, Integer.class);
			Integer uid = query2.getSingleResult();
			userId = uid + 1;
		}
		users.setUserId(userId);
		return daoObject.createAccount(users);
	}

	@Override
	public Integer validateUser(String uName, String password) {
		// TODO Auto-generated method stub
		return daoObject.validateUser(uName, password);
	}

	@Override
	public List<Flights> searchFlight(String source, String dest) {
		// TODO Auto-generated method stub
		return daoObject.searchFlight(source, dest);
	}

	@Override
	public Integer bookFlight(int flightid, Integer userid) {
		Integer tBD = bookingId;
		Tickets bd = new Tickets();
		Flights fd = daoObject.getFlightById(flightid);

		if (fd.getAvailableSeats() > 0) {
			fd.setAvailableSeats(fd.getAvailableSeats()-1);
			daoObject.updateFlight(fd);

			String command = "select count(bd.bookingId) from Tickets bd";
			TypedQuery<Long> query1 = entityManager.createQuery(command, Long.class);
			long count = query1.getSingleResult();
			
			
			if (count>0) {
				command = "select max(bd.bookingId) from Tickets bd";
				TypedQuery<Integer> query2 = entityManager.createQuery(command, Integer.class);
				Integer bookid = query2.getSingleResult();
				tBD = bookid + 1;
			} else {
				tBD =200;
			}

			
			command = "select user from Users user where user.userId =: puserid";
			TypedQuery<Users> query2 = entityManager.createQuery(command, Users.class);
			query2.setParameter("puserid", userid);

			Users user = query2.getSingleResult();
			bd.setBookingId(tBD);
			bd.setuser(user);
			bd.setFlightData(fd);

			if (daoObject.addBooking(bd)) {
				return tBD;
			}

		} else {
			System.out.println("No booking available");
		}
		return 0;
	}

	@Override
	public boolean cancelFlight(Integer bookid) {
//		Tickets bd =daoObject.getBookingDetails(bookingId);
		
		String qStr = "SELECT t FROM Tickets t WHERE t.bookingId =: bId";
		TypedQuery<Tickets> query = entityManager.createQuery(qStr, Tickets.class);
		query.setParameter("bId", bookid);
		Tickets bd = query.getSingleResult();

		if (bd != null) {
			int availableSeats = bd.getFlightData().getAvailableSeats() + 1;
			bd.getFlightData().setAvailableSeats(availableSeats);
			
			daoObject.cancelFlight(bd);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Tickets> viewTickets(int userid) {
		return daoObject.viewTickets(userid);
	}
	
	

}
