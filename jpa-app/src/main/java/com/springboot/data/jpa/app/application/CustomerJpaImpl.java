package com.springboot.data.jpa.app.application;

import com.springboot.data.jpa.app.api.CustomerJpaApi;
import com.springboot.data.jpa.app.model.request.CustomerRequest;
import com.springboot.data.jpa.app.model.response.CustomerResponse;
import com.springboot.data.jpa.app.service.CustomerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerJpaImpl implements CustomerJpaApi {

    @Autowired
    private CustomerJpaService customerJpaService;

    @Override
    public UUID createCustomer(CustomerRequest customerRequest) {
        return this.customerJpaService.createCustomer(customerRequest);
    }

    @Override
    public CustomerResponse getCustomer(UUID customerId) {
        return this.customerJpaService.getCustomer(customerId);
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerRequest customerRequest) {
        this.customerJpaService.updateCustomer(customerId, customerRequest);
    }
}
