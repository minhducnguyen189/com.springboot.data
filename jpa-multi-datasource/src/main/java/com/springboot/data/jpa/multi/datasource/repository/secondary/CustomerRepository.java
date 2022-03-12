package com.springboot.data.jpa.multi.datasource.repository.secondary;

import com.springboot.data.jpa.multi.datasource.entity.secondary.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
