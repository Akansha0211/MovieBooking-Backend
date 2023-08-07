package com.akansha.movieBooking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akansha.movieBooking.entities.Ticket;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	
	@Query("SELECT t FROM Ticket t WHERE t.movie.movieId =:movieId")
	List<Ticket> findByMovieId(@Param("movieId") int movieId);
}
