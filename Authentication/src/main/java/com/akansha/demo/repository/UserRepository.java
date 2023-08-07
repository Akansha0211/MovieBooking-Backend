package com.akansha.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akansha.demo.model.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{

	// can have role implementation & secret question
		@Query(value = "select u from User u where u.userName=:username and u.password=:password")
		public User validateUser(@Param("username") String userName,@Param("password") String password); // LOGIN
	
		@Query(value="select u from User u where u.userName =:username")
		public User findByName(@Param("username") String userName);
}
