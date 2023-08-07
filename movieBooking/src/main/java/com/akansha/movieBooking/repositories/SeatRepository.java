package com.akansha.movieBooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.akansha.movieBooking.entities.Seat;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SeatRepository extends JpaRepository<Seat, Integer> {
	
	@Query(value="select s from Seat s where s.id =:id")
	public Seat findById(@Param("id") int id);
}
