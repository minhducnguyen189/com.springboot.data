package com.springboot.data.hibernate.envers.app.entity;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Audited
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date dob;

    private OffsetDateTime createdDate;

    private OffsetDateTime updatedDate;

    @NotAudited
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public OffsetDateTime getUpdatedDate() {
        return updatedDate;
    }


    @PrePersist
    private void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedDate = OffsetDateTime.now();
    }

}
