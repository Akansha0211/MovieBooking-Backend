package com.akansha.movieBooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akansha.movieBooking.entities.Ticket;
import com.akansha.movieBooking.service.TicketService;

@RestController
@RequestMapping("tickets")
@CrossOrigin(origins = "*")
public class TicketController {
	
	@Autowired
	private TicketService ts;
	
//	@PostMapping("/createTicket")
//	public ResponseEntity<?> createTicket(@RequestBody Ticket ticket){
//		if(ts.createTicket(ticket) != null) {
//			return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
//		}
//		return new ResponseEntity<String>("Ticket is null", HttpStatus.NO_CONTENT);
//	}

	
	// THERE IS NO USE OF THIS API ENDPOINT
	
//	@GetMapping("/{transactionId}")
//	public ResponseEntity<?> getTicketById(@PathVariable int transactionId){
//		Ticket ticket = ts.getTicketById(transactionId);
//		if(ticket != null) {
//			return ResponseEntity.ok(ticket);
//		}
//		return new ResponseEntity<String>("Ticket is null", HttpStatus.NO_CONTENT);
//	}
	
	@GetMapping("/movies/{movieId}")
	public ResponseEntity<?> getAllTicketsForMovie(@PathVariable int movieId){
		List<Ticket> tickets = ts.getAllTickets(movieId);
		//return ResponseEntity.ok(tickets);
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
	}
	
	// UPDATE TICKET IS ALSO USELESS ENDPOINT
//	@PutMapping("/updateTicket")
//	public ResponseEntity<?> updateTicket(@RequestBody Ticket ticket){
//		if(ts.updateTicket(ticket)!= null) {
//			return new ResponseEntity<Ticket>(HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("Ticket not updated",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	// DELETE (CANCEL) TICKET CAN BE IN REAL WORLD SCENARIO 
	// BUT CASE STUDY DOES NOT REQUIRE THIS METHOD
//	@DeleteMapping("/cancel/transactionId")
//	public ResponseEntity<?> deleteTicket(@PathVariable int transactionId) {
//		if(ts.deleteTicket(transactionId)) {
//			return new ResponseEntity<String>("Ticket is cancelled", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("Ticket not found", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
}
