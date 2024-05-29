package com.rutik.spring_security.service.jwt;

import com.rutik.spring_security.entity.CustomerEntity;
import com.rutik.spring_security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerServiceImpl implements UserDetailsService {

    private final CustomerRepository g_ObjCustomerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository gObjCustomerRepository) {
        g_ObjCustomerRepository = gObjCustomerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Write logic code to fetch customer form DB
        CustomerEntity l_Customer = g_ObjCustomerRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Username Or Email Not found : "+email));
        return new User(l_Customer.getName() , l_Customer.getPassword() , Collections.emptyList());
    }
}
