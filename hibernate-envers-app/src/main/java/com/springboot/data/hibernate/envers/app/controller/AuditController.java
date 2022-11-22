package com.springboot.data.hibernate.envers.app.controller;

import com.springboot.data.hibernate.envers.app.model.response.CustomerResponse;
import com.springboot.data.hibernate.envers.app.service.AuditService;
import com.springboot.data.hibernate.envers.app.service.CustomerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AuditController {

    @Autowired
    private AuditService auditService;

    @RequestMapping(method = RequestMethod.GET, path = "/v1/jpa/customers/{customerId}/audit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerResponse>> getCustomerAudit(@PathVariable(name = "customerId") UUID customerId) {
        return new ResponseEntity<>(this.auditService.getAuditCustomer(customerId), HttpStatus.OK);
    }


}
