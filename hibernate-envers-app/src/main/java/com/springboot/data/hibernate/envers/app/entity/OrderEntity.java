package com.springboot.data.hibernate.envers.app.entity;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Audited
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    private String orderName;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdatedDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private OffsetDateTime createdDateSystem;

    private OffsetDateTime updatedDate;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @NotAudited
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemEntity> items = new ArrayList<>();


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @PrePersist
    private void setCreatedDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createdDate = localDateTime;
        this.lastUpdatedDate = localDateTime;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @PreUpdate
    private void setLastUpdatedDate() {
        this.lastUpdatedDate = LocalDateTime.now();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public OffsetDateTime getCreatedDateSystem() {
        return createdDateSystem;
    }

    public OffsetDateTime getUpdatedDate() {
        return updatedDate;
    }

    @PrePersist
    private void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdDateSystem = now;
        this.updatedDate = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedDate = OffsetDateTime.now();
    }

}
