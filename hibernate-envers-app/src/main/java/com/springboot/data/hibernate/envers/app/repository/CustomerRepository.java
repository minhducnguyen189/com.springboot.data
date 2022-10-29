package com.springboot.data.hibernate.envers.app.repository;

import com.springboot.data.hibernate.envers.app.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
