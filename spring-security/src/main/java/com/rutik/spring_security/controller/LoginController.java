package com.rutik.spring_security.controller;

import com.rutik.spring_security.dto.LoginRequest;
import com.rutik.spring_security.dto.LoginResponce;
import com.rutik.spring_security.service.jwt.CustomerServiceImpl;
import com.rutik.spring_security.service.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    public final AuthenticationManager authenticationManager;
    public final CustomerServiceImpl customerService;

    public final JwtUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, CustomerServiceImpl customerService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/logindetail")
    public ResponseEntity<LoginResponce> login(@RequestBody LoginRequest loginRequst){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequst.getEmail() , loginRequst.getPassword())
            );
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails;
        try{
            userDetails = customerService.loadUserByUsername(loginRequst.getEmail());
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //here Apply JWT Token must Be implement jwtutil
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Additional logic can be added here if needed

        return ResponseEntity.ok(new LoginResponce(jwt));
    }
}
