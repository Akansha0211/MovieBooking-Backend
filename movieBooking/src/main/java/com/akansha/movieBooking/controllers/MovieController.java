package com.akansha.movieBooking.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.akansha.movieBooking.entities.Movie;
import com.akansha.movieBooking.requestHandler.BookTicketRequest;
import com.akansha.movieBooking.service.KafkaProducerService;
import com.akansha.movieBooking.service.MovieService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class MovieController {

	@Autowired
	MovieService ms;
	
	@Autowired
	KafkaProducerService kp;
	
	// get all movies
	@GetMapping("/getAllmovies")
	public ResponseEntity<?> getMovies(){
		List<Movie> movieList = ms.getAllMovies();
		
		// Send a message to the Kafka consumer
        String message = "A new movie has been added.";
        kp.setTempMsg(message);
        
		if(movieList != null) {
			return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Access-Control-Allow-Origin", "http://localhost:4200");
//			return ResponseEntity.ok().headers(headers).body(movieList);
		}
		return new ResponseEntity<String>("Book List is Empty", HttpStatus.NO_CONTENT);
		
	}
	
	// get movie by id
	@GetMapping("/moviId/{movieId}")
	public ResponseEntity<?> getMovieById(@PathVariable("movieId") int movieId){
		Movie movieById = ms.getMovieById(movieId);
		if(movieById != null) {
			return ResponseEntity.ok().body(movieById);
		}
		return new ResponseEntity<String>("Movie not found", HttpStatus.NO_CONTENT);
	}
	
	// add movie
	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		if(ms.addMovie(movie) != null) {
			
			return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Movie is null", HttpStatus.CONFLICT);
	}
	
	// update movie : NOT NEEDED ( can rather delete + addMovie)
//	@PutMapping("/updateMovie")
//	public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
//		if(ms.updateMovie(movie)) {
//			return new ResponseEntity<Movie>(HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("Movie is not updated", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	// delete movie : working fine as we using cascade while mapping Tickets to movie
	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable("movieId") int movieId){
		if(ms.deleteMovie(movieId)) {
			//return new ResponseEntity<String>("Movie is successfully deleted", HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<String>("Book could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@PostMapping("/{movieId}/book")
//	public ResponseEntity<?> bookTicket(@PathVariable int movieId , @RequestBody Ticket ticket){
//		ms.bookTicket(movieId, ticket);
//		return ResponseEntity.ok("Ticket booked successfully");
//	}
	
	@PostMapping(value="/{movieId}/book")
	public ResponseEntity<?> bookTicket(@PathVariable int movieId, @RequestBody BookTicketRequest bookRequest){
		//ms.bookTicket(movieId, request);
		ms.bookTicket(movieId, bookRequest.getBookingRequired());
		//return ResponseEntity.ok("Ticket booked successfully");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
