package com.springboot.data.jpa.app.service;

import com.springboot.data.jpa.app.entity.CustomerEntity;
import com.springboot.data.jpa.app.entity.OrderEntity;
import com.springboot.data.jpa.app.entity.OrderStatus;
import com.springboot.data.jpa.app.model.request.CustomerRequest;
import com.springboot.data.jpa.app.model.request.ItemRequest;
import com.springboot.data.jpa.app.model.request.OrderRequest;
import com.springboot.data.jpa.app.model.response.CustomerResponse;
import com.springboot.data.jpa.app.repository.CustomerRepository;
import com.springboot.data.jpa.app.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerJpaService {

    @Autowired
    private CustomerRepository customerRepository;

    public UUID createCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = this.toCustomerEntity(new CustomerEntity(), customerRequest);
        if (customerRequest.getOrders() != null && !customerRequest.getOrders().isEmpty()) {
            List<OrderRequest> orderRequests = customerRequest.getOrders();
            List<OrderEntity> orderEntities = this.toOrderEntities(orderRequests);
            orderEntities.forEach(o -> o.setCustomer(customerEntity));
            customerEntity.setOrders(orderEntities);
        }
        return this.customerRepository.save(customerEntity).getId();
    }

    public CustomerResponse getCustomer(UUID customerId) {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(customerId);
        if (customerEntity.isPresent()) {
            return customerEntity.get().toCustomerResponse();
        }
        throw new RuntimeException("Customer Not Found!");
    }

    private List<OrderEntity> toOrderEntities(List<OrderRequest> orderRequests) {
        return orderRequests.stream().map(o -> this.toOrderEntity(new OrderEntity(), o)).collect(Collectors.toList());
    }

    private OrderEntity toOrderEntity(OrderEntity orderEntity, OrderRequest orderRequest) {
        orderEntity.setOrderName(orderRequest.getOrderName());
        orderEntity.setOrderStatus(OrderStatus.CREATED);
        if (orderRequest.getItems() != null && !orderRequest.getItems().isEmpty()) {
            List<ItemEntity> itemEntities = this.toItemEntities(orderRequest.getItems());
            itemEntities.forEach(i -> i.setOrder(orderEntity));
            orderEntity.setItems(itemEntities);
        }
        return orderEntity;
    }

    private List<ItemEntity> toItemEntities(List<ItemRequest> itemRequests) {
        return itemRequests.stream().map(i -> this.toItemEntity(new ItemEntity(), i)).collect(Collectors.toList());
    }

    private ItemEntity toItemEntity(ItemEntity itemEntity, ItemRequest item) {
        itemEntity.setItemName(item.getItemName());
        itemEntity.setQuantity(item.getQuantity());
        itemEntity.setPrice(item.getPrice());
        return itemEntity;
    }

    private CustomerEntity toCustomerEntity(CustomerEntity customerEntity, CustomerRequest customerRequest) {
        customerEntity.setAddress(customerRequest.getAddress());
        customerEntity.setDob(customerRequest.getDob());
        customerEntity.setEmail(customerRequest.getEmail());
        customerEntity.setGender(customerRequest.getGender());
        customerEntity.setFullName(customerRequest.getFullName());
        customerEntity.setPhone(customerRequest.getPhone());
        return customerEntity;
    }

}
