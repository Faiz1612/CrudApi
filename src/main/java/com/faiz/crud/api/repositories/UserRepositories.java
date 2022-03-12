package com.faiz.crud.api.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faiz.crud.api.models.UserDetails;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<UserDetails, Integer>{
	Optional<UserDetails>  findByUserName(String userName );
}
