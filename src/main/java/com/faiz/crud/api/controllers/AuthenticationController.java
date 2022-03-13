package com.faiz.crud.api.controllers;

import com.faiz.crud.api.config.JwtUtils;
import com.faiz.crud.api.models.JwtToken;
import com.faiz.crud.api.models.UserInformation;
import com.faiz.crud.api.repositories.UserRepositories;
import com.faiz.crud.api.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/signUp")
    public ResponseEntity<JwtToken> signUp(@RequestBody UserInformation userInformation){
        userRepositories.save(userInformation);

        return authenticate(userInformation);
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtToken> signIn(@RequestBody UserInformation userInformation){
        return authenticate(userInformation);
    }

    private ResponseEntity<JwtToken> authenticate(UserInformation userInformation){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInformation.getUserName(),userInformation.getPassword()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new JwtToken("Bad Credentials"));
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userInformation.getUserName());
        String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtToken(token));
    }
}
