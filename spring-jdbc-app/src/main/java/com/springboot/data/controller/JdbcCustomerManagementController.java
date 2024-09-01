package com.springboot.data.controller;

import com.springboot.data.model.request.CustomerRequest;
import com.springboot.data.service.CustomerJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcCustomerManagementController {

    private final CustomerJdbcService customerJdbcService;

    @Autowired
    public JdbcCustomerManagementController(CustomerJdbcService customerJdbcService) {
        this.customerJdbcService = customerJdbcService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/jdbc/customers", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<String>(String.valueOf(this.customerJdbcService.createCustomer(customerRequest)), HttpStatus.CREATED);
    }

}
