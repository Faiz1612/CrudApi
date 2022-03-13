package com.faiz.crud.api.service;

import com.faiz.crud.api.models.MyUserDetails;
import com.faiz.crud.api.models.UserInformation;
import com.faiz.crud.api.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInformation> userInformation = userRepositories.findById(username);
        if(userInformation.isPresent()){
            return new MyUserDetails(userInformation.get());
        }
       else {
           throw new UsernameNotFoundException("User Not Found");
        }
    }
}
