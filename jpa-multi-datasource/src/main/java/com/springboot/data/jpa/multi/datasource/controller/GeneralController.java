package com.springboot.data.jpa.multi.datasource.controller;

import com.springboot.data.jpa.multi.datasource.model.Customer;
import com.springboot.data.jpa.multi.datasource.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/primary/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> saveDataToPrimarySchema(@RequestBody Customer customer) {
        return new ResponseEntity<>(this.generalService.saveDataToPrimaryCustomerSchema(customer), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/secondary/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> saveDataToSecondarySchema(@RequestBody Customer customer) {
        return new ResponseEntity<>(this.generalService.saveDataToSecondaryCustomerSchema(customer), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/schemas/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UUID>> saveDataToAllSchemas(@RequestBody Customer customer) {
        return new ResponseEntity<>(this.generalService.saveDataToAllSchemas(customer), HttpStatus.CREATED);
    }

}
