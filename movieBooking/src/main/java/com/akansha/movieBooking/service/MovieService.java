package com.akansha.movieBooking.service;

import java.util.List;

import com.akansha.movieBooking.entities.Movie;
import com.akansha.movieBooking.entities.Ticket;
import com.akansha.movieBooking.requestHandler.BookTicketRequest;

public interface MovieService {
	
	public List<Movie> getAllMovies();
	
	public Movie addMovie(Movie movie);
	
	//public boolean updateMovie(Movie movie);
	
	public boolean deleteMovie(int movieId);
	
	public Movie getMovieById(int movieId);
	
	//public void bookTicket(int movieId, Ticket ticket);
	

	public void bookTicket(int movieId, int bookingsReq);
	//public void bookTicket(int movieId, int bookingsReq);
	
	// getAllMovies
	// addMovie
	// updateMovie
	// deleteMovie
	// getMovieById
}
