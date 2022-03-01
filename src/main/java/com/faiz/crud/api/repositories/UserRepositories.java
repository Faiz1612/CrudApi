package com.faiz.crud.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faiz.crud.api.models.UserDetails;

@Repository
public interface UserRepositories extends JpaRepository<UserDetails, Integer>{
	
}
