package com.springboot.data.jdbc.app.application;

import com.springboot.data.jdbc.app.api.CustomerJDBCApi;
import com.springboot.data.jdbc.app.model.request.CustomerRequest;
import com.springboot.data.jdbc.app.service.CustomerJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerJDBCImpl implements CustomerJDBCApi {

    @Autowired
    private CustomerJdbcService customerJdbcService;

    @Override
    public UUID createCustomer(CustomerRequest customerRequest) {
        return this.customerJdbcService.createCustomer(customerRequest);
    }
}
