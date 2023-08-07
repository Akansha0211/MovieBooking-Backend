package com.akansha.movieBooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	private int totalSeats;
	private int availableSeats;
	private int bookedSeats;
	
	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movie movie;
			
	public Ticket() {
	}
	
	public Ticket(int transactionId, int totalSeats, int availableSeats, int bookedSeats, Movie movie) {
		this.transactionId = transactionId;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.bookedSeats = bookedSeats;
		this.movie = movie;
	}

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public int getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
		
}
