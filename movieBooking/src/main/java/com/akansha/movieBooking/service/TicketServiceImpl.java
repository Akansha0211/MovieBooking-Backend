package com.akansha.movieBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akansha.movieBooking.entities.Ticket;
import com.akansha.movieBooking.repositories.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketRepository ticketRepo;

//	@Override
//	public Ticket createTicket(Ticket ticket) {
//		// TODO Auto-generated method stub
//		return ticketRepo.save(ticket);
//	}

//	@Override
//	public Ticket getTicketById(int transactionId) {
//		// TODO Auto-generated method stub
//		return ticketRepo.findById(transactionId).orElse(null);
//	}

	@Override
	public List<Ticket> getAllTickets(int movieId) {
		// TODO Auto-generated method stub
		
		List<Ticket> ticketList = ticketRepo.findByMovieId(movieId); // this method was defined in jpa repository
		if(ticketList != null) {
			return ticketList;
		}
		return null;
		
		//return ticketRepo.findAllById(movieId)
	}

//	@Override
//	public Ticket updateTicket(Ticket ticket) {
//		
//		//Optional<Ticket> ticket = ticketRepo.findById(transactionId);
////		Ticket ticket = ticketRepo.findById(transactionId).orElse(null);
//		if(ticket!= null) {
//			ticket.setBookedSeats(ticket.getBookedSeats());
//			ticket.setAvailableSeats(ticket.getAvailableSeats());
//			return ticketRepo.save(ticket);
//		}
//		return null;
//	}

//	@Override
//	public boolean deleteTicket(int transactionId) {
//		// TODO Auto-generated method stub
//		
////		ticketRepo.deleteById(transactionId);
////		return true;
//		Ticket ticket = ticketRepo.findById(transactionId).orElse(null);
//		if(ticket != null) {
//			ticketRepo.delete(ticket);
//			return true;
//		}
//		return false;
//	}
	
	
	
	
}
