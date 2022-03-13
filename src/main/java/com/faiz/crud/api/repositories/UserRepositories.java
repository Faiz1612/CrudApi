package com.faiz.crud.api.repositories;

import com.faiz.crud.api.models.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<UserInformation, String>{
}
