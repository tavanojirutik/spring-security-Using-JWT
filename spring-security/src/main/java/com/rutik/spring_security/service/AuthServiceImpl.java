package com.rutik.spring_security.service;

import com.rutik.spring_security.dto.CustomerDto;
import com.rutik.spring_security.entity.CustomerEntity;
import com.rutik.spring_security.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final CustomerRepository g_ObjCustomerRepository;

    private final PasswordEncoder g_PasswordEncoder;

    @Autowired
    public AuthServiceImpl(CustomerRepository g_ObjCustomerRepository, PasswordEncoder g_PasswordEncoder) {
        this.g_ObjCustomerRepository = g_ObjCustomerRepository;
        this.g_PasswordEncoder = g_PasswordEncoder;
    }

    @Override
    public boolean createCustomer(CustomerDto p_CustomerDto) {
        //Check if Customer already exist
        if (g_ObjCustomerRepository.existsByEmail(p_CustomerDto.getEmail())){
            return false;
        }

        CustomerEntity l_CustomerEntity = new CustomerEntity();
        BeanUtils.copyProperties(p_CustomerDto,l_CustomerEntity);

        //Hash the Password before saving
        String hashPassword = g_PasswordEncoder.encode(p_CustomerDto.getPassword());
        l_CustomerEntity.setPassword(hashPassword);
        g_ObjCustomerRepository.save(l_CustomerEntity);

        return true;
    }
}
