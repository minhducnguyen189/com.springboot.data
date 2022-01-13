package com.springboot.data.jdbc.app.api;

import com.springboot.data.jdbc.app.model.request.CustomerRequest;

import java.util.UUID;

public interface CustomerJDBCApi {

    UUID createCustomer(CustomerRequest customerRequest);

}
