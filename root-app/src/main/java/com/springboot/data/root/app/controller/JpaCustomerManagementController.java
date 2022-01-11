package com.springboot.data.root.app.controller;

import com.springboot.database.transaction.jpa.app.model.request.CustomerRequest;
import com.springboot.database.transaction.jpa.app.service.CustomerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaCustomerManagementController {

    @Autowired
    private CustomerJpaService customerJpaService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/customers", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<String>(String.valueOf(this.customerJpaService.createCustomer(customerRequest)), HttpStatus.CREATED);
    }


}
