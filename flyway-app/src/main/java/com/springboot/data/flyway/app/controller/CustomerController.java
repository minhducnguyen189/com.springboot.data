package com.springboot.data.flyway.app.controller;

import com.springboot.data.flyway.app.model.Customer;
import com.springboot.data.flyway.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> saveDataToPrimarySchema(@RequestBody Customer customer) {
        return new ResponseEntity<>(this.customerService.createCustomer(customer), HttpStatus.CREATED);
    }

}
