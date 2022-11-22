package com.springboot.data.hibernate.envers.app.service;

import com.springboot.data.hibernate.envers.app.entity.CustomerEntity;
import com.springboot.data.hibernate.envers.app.model.response.CustomerResponse;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuditService {

    @Autowired
    private EntityManager entityManager;

    public List<CustomerResponse> getAuditCustomer(UUID customerId) {
        List<CustomerEntity> auditEntities = AuditReaderFactory.get(entityManager)
                .createQuery()
                .forRevisionsOfEntity(CustomerEntity.class, true,false)
                .add(AuditEntity.property("id").eq(customerId))
                .addOrder(AuditEntity.property("createdDate").desc())
                .addOrder(AuditEntity.property("updatedDate").desc())
                .getResultList();
        return auditEntities.stream().map(this::toCustomerResponse).collect(Collectors.toList());
    }

    private CustomerResponse toCustomerResponse(CustomerEntity customerEntity) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customerEntity.getId());
        customerResponse.setAddress(customerEntity.getAddress());
        customerResponse.setDob(customerEntity.getDob());
        customerResponse.setEmail(customerEntity.getEmail());
        customerResponse.setPhone(customerEntity.getPhone());
        customerResponse.setFullName(customerEntity.getFullName());
        customerResponse.setGender(customerEntity.getGender());
        customerResponse.setCreatedDate(customerEntity.getCreatedDate());
        customerResponse.setUpdatedDate(customerEntity.getUpdatedDate());
        return customerResponse;
    }

}
