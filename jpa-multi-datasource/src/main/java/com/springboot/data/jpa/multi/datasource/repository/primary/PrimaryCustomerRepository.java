package com.springboot.data.jpa.multi.datasource.repository.primary;

import com.springboot.data.jpa.multi.datasource.entity.primary.PrimaryCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrimaryCustomerRepository extends JpaRepository<PrimaryCustomerEntity, UUID> {
}
