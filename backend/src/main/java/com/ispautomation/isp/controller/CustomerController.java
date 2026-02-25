package com.ispautomation.isp.controller;

import com.ispautomation.isp.model.Customer;
import com.ispautomation.isp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getCustomers() {
        return service.getAllCustomers();
    }

    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
        return "Customer added successfully";
    }
}