package com.akansha.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.akansha.demo.model.Role;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer>{

	
//	@Query("SELECT r FROM Role r WHERE r.name= :name")
//	Optional<Role> findByName(@Param("name") String name);
}
