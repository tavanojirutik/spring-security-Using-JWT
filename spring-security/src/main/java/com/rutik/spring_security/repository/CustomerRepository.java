package com.rutik.spring_security.repository;

import com.rutik.spring_security.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmail(String email);
    Optional<CustomerEntity> findByEmail(String email);
}
