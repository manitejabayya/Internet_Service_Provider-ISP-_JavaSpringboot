package com.ispautomation.isp.service;

import com.ispautomation.isp.model.Customer;
import com.ispautomation.isp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public void addCustomer(Customer customer) {
        repository.save(customer);
    }
}