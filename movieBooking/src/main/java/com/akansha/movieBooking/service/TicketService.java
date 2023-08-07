package com.akansha.movieBooking.service;

import java.util.List;

import com.akansha.movieBooking.entities.Ticket;

public interface TicketService {
	//public Ticket createTicket(Ticket ticket);
	//public Ticket getTicketById(int transactionId);
	public List<Ticket> getAllTickets(int movieId);
	// update ticket
	//public Ticket updateTicket(Ticket ticket);
	// delete ticket
	//public boolean deleteTicket(int transactionId);
}
