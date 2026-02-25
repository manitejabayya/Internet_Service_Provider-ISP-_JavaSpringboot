package com.ispautomation.isp.repository;

import com.ispautomation.isp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository layer
 * Talks to database automatically using JPA
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // No code needed
    // Spring auto-creates:
    // save()
    // findAll()
    // findById()
    // deleteById()

}