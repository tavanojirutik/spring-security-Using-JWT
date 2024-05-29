package com.rutik.spring_security.controller;


import com.rutik.spring_security.dto.CustomerDto;
import com.rutik.spring_security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class SignUpController {

    public final AuthService g_objAuthService;

    @PostMapping("/sighupuser")
    public ResponseEntity<String> signupCustomer(@RequestBody CustomerDto p_CustomerDto){
        boolean isUserCreated = g_objAuthService.createCustomer(p_CustomerDto);
        if (isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer Created Successfully ");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer ");
        }
    }
}
