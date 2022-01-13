package com.springboot.data.jpa.app.api;

import com.springboot.data.jpa.app.model.request.CustomerRequest;
import com.springboot.data.jpa.app.model.response.CustomerResponse;

import java.util.UUID;

public interface CustomerJpaApi {

    UUID createCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomer(UUID customerId);
    void updateCustomer(UUID customerId, CustomerRequest customerRequest);
    void deleteCustomer(UUID customerId);
}
