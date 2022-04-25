package com.springboot.data.flyway.app.service;

import com.springboot.data.flyway.app.entity.CustomerEntity;
import com.springboot.data.flyway.app.model.Customer;
import com.springboot.data.flyway.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public UUID createCustomer(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setFullName(customer.getFullName());
        customerEntity.setPhone(customer.getPhone());
        customerEntity.setDob(customer.getDob());
        return customerRepository.save(customerEntity).getId();
    }

}
