package com.akansha.movieBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.akansha.movieBooking.entities.Movie;
import com.akansha.movieBooking.entities.Ticket;
import com.akansha.movieBooking.repositories.MovieRepository;
import com.akansha.movieBooking.repositories.TicketRepository;
import com.akansha.movieBooking.requestHandler.BookTicketRequest;

import jakarta.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private TicketRepository ticketRepo;
	
//	@Autowired 
//	private BookTicketRequest request;

	@Override
	public List<Movie> getAllMovies() {
		
//		List<Movie> movieList = movieRepo.findAll();
//		if(movieList != null) {
//			return movieList;
//		}
//		return null;
		return movieRepo.findAll();
	}

	@Override
	public Movie addMovie(Movie movie) {
//		movie.setAvailableSeats(100); // initially when we are adding a movie then the availabale Seats should be 100
//		return movieRepo.save(movie);
		
		// we are facing duplicate movie issue to resolve this updating the code
		// to resolve this we will check if the moviename matches we will throws Exception
		
		List<Movie> existingMovies = movieRepo.findAll();
		for(Movie existingMovie : existingMovies) {
			if(existingMovie.getMovieName().equals(movie.getMovieName())) {
				//throw new RuntimeException("Movie with name : "  + movie.getMovieName() + "already exists");
				return null;
			}
		}
		movie.setAvailableSeats(100); // initially when we are adding a movie then the availabale Seats should be 100
		movie.setTotalSeats(100);
		
		//this.tempMsg.send(topic, movie.getMovieName());
		return movieRepo.save(movie);
		
		
		
		
	}

//	@Override
//	@Transactional
//	public boolean updateMovie(Movie movie) {
//		// TODO Auto-generated method stub
//		//Movie movie1 = movieRepo.getById(movie.getMovieId());
//		Movie existingMovie = movieRepo.findById(movie.getMovieId()).orElse(null);
//		if(existingMovie != null) {
//			existingMovie.setMovieName(movie.getMovieName());
//			existingMovie.setAvailableSeats(movie.getAvailableSeats());
//			existingMovie.setTheatreName(movie.getTheatreName());
//			
//			movieRepo.save(existingMovie);
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean deleteMovie(int movieId) {
		// TODO Auto-generated method stub
		movieRepo.deleteById(movieId);
		return true;
	}

	@Override
	public Movie getMovieById(int movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movie = movieRepo.findById(movieId);
		if(movie.isPresent()) {
			return movie.get();
		}
		return null;
	}


	
	@Override
	@Transactional
	public void bookTicket(int movieId, int bookingsReq) {
		// TODO Auto-generated method stub
		
		// doubt : if we are already using Transactional in Movie Repo so do we really need it for its serviceImpl methods
		
		
		Movie movie = movieRepo.findById(movieId).orElse(null);
		
		if(movie != null) {
			if(movie.getAvailableSeats() >= bookingsReq) {
				// update available seats in movie
				
				//movie.setAvailableSeats(movie.getAvailableSeats()-1);
				movie.setAvailableSeats(movie.getAvailableSeats() - bookingsReq);
				
				//movie.setTotalSeats(100);
				movieRepo.save(movie);
				// update booked seats and total seats  and available seats
				
//				@Autowired
//				Ticket ticket; not working inside method
				
				Ticket ticket = new Ticket();
				ticket.setMovie(movie);
				ticket.setTotalSeats(100); // as specified in requirement
				//ticket.setBookedSeats(ticket.getBookedSeats()+1);
				
//				ticket.setAvailableSeats((ticket.getTotalSeats() - ticket.getBookedSeats())- bookingsReq);
//				ticket.setBookedSeats(ticket.getBookedSeats() + bookingsReq);
				
				ticket.setBookedSeats(ticket.getBookedSeats() + bookingsReq);
				ticket.setAvailableSeats(ticket.getTotalSeats() - ticket.getBookedSeats());
				
//				ticket.setBookedSeats(ticket.getTotalSeats() - movie.getAvailableSeats() + bookingsReq);
//				
//				ticket.setAvailableSeats(ticket.getTotalSeats() - ticket.getBookedSeats() - bookingsReq);
				
				ticketRepo.save(ticket);
			}
			else throw new RuntimeException("No Seats Avaialable");
		}
		else {
			throw new RuntimeException("Movie Not Found");
		}
		
	}

	
}
