package com.springboot.data.hibernate.envers.app.controller;


import com.springboot.data.hibernate.envers.app.model.request.CustomerRequest;
import com.springboot.data.hibernate.envers.app.model.response.CustomerResponse;
import com.springboot.data.hibernate.envers.app.service.CustomerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class JpaCustomerManagementController {

    @Autowired
    private CustomerJpaService customerJpaService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/jpa/customers", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<String>(String.valueOf(this.customerJpaService.createCustomer(customerRequest)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/jpa/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable(name = "customerId") UUID customerId) {
        return new ResponseEntity<CustomerResponse>(this.customerJpaService.getCustomer(customerId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/v1/jpa/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable(name = "customerId") UUID customerId,
                                                 @RequestBody CustomerRequest customerRequest) {
        this.customerJpaService.updateCustomer(customerId, customerRequest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/v1/jpa/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "customerId") UUID customerId) {
        this.customerJpaService.deleteCustomer(customerId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
