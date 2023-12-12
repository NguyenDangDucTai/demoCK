package com.example.onthick.fontend.services;


import com.example.onthick.fontend.models.Customer;
import com.example.onthick.fontend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
