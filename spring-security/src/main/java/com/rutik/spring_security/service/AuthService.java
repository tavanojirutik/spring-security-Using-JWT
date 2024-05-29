package com.rutik.spring_security.service;

import com.rutik.spring_security.dto.CustomerDto;

public interface AuthService {
    boolean createCustomer(CustomerDto pCustomerDto);
}
