package com.springboot.data.jpa.multi.datasource.service;

import com.springboot.data.jpa.multi.datasource.entity.primary.PrimaryCustomerEntity;
import com.springboot.data.jpa.multi.datasource.entity.secondary.CustomerEntity;
import com.springboot.data.jpa.multi.datasource.model.Customer;
import com.springboot.data.jpa.multi.datasource.repository.primary.PrimaryCustomerRepository;
import com.springboot.data.jpa.multi.datasource.repository.secondary.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
/**
 use  @EnableTransactionManagement to enable using Transactional Management
 **/
@EnableTransactionManagement
public class GeneralService {

    @Autowired
    private PrimaryCustomerRepository primaryCustomerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     use @Transactional with value (bean name of primaryTransactionManager)
     so the Jpa will know which transaction that we want to use.
     **/
    @Transactional(value = "primaryTransactionManager")
    public UUID saveDataToPrimaryCustomerSchema(Customer customer) {
        PrimaryCustomerEntity primaryCustomerEntity = new PrimaryCustomerEntity();
        primaryCustomerEntity.setAddress(customer.getAddress());
        primaryCustomerEntity.setEmail(customer.getEmail());
        primaryCustomerEntity.setFullName(customer.getFullName());
        primaryCustomerEntity.setPhone(customer.getPhone());
        primaryCustomerEntity.setDob(customer.getDob());
        return primaryCustomerRepository.save(primaryCustomerEntity).getId();
    }

    /**
     use @Transactional with value (bean name of secondaryTransactionManager)
     so the Jpa will know which transaction that we want to use.
     **/
    @Transactional(value = "secondaryTransactionManager")
    public UUID saveDataToSecondaryCustomerSchema(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setFullName(customer.getFullName());
        customerEntity.setPhone(customer.getPhone());
        customerEntity.setDob(customer.getDob());
        return customerRepository.save(customerEntity).getId();
    }

    /**
     use @Transactional with value (bean name of chainedTransactionManager)
     so the Jpa will know which transaction that we want to use.
     If saving data to secondary is failed, so the data
     which is saved in primary DB will be reverted.
     **/
    @Transactional(value = "chainedTransactionManager")
    public List<UUID> saveDataToAllSchemas(Customer customer) {
        UUID primaryCustomerId = saveDataToPrimaryCustomerSchema(customer);
        UUID customerId = saveDataToSecondaryCustomerSchema(customer);
        /**
         this comment function is used for revert all data in DB
         when this function is executed successfully
         **/
        // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return Arrays.asList(primaryCustomerId, customerId);
    }

}
