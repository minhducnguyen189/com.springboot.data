package com.springboot.data.root.app.controller;

import com.springboot.data.jpa.app.api.CustomerJpaApi;
import com.springboot.data.jpa.app.model.request.CustomerRequest;
import com.springboot.data.jpa.app.model.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class JpaCustomerManagementController {

    @Autowired
    private CustomerJpaApi customerJpaApi;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/jpa/customers", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<String>(String.valueOf(this.customerJpaApi.createCustomer(customerRequest)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/jpa/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable(name = "customerId") UUID customerId) {
        return new ResponseEntity<CustomerResponse>(this.customerJpaApi.getCustomer(customerId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/v1/jpa/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable(name = "customerId") UUID customerId,
                                                 @RequestBody CustomerRequest customerRequest) {
        this.customerJpaApi.updateCustomer(customerId, customerRequest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/v1/jpa/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "customerId") UUID customerId) {
        this.customerJpaApi.deleteCustomer(customerId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
