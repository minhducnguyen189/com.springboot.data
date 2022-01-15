package com.springboot.data.root.app.controller;

import com.springboot.data.jdbc.app.api.CustomerJDBCApi;
import com.springboot.data.jdbc.app.model.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JdbcCustomerManagementController {

    @Autowired(required = false)
    private CustomerJDBCApi customerJDBCApi;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/jdbc/customers", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<String>(String.valueOf(this.customerJDBCApi.createCustomer(customerRequest)), HttpStatus.CREATED);
    }

}
