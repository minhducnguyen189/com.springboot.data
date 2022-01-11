package com.springboot.data.jpa.app.service;

import com.springboot.data.jpa.app.entity.OrderStatus;
import com.springboot.data.jpa.app.model.request.ItemRequest;
import com.springboot.data.jpa.app.model.request.OrderRequest;
import com.springboot.data.jpa.app.repository.CustomerRepository;
import com.springboot.data.jpa.app.repository.OrderRepository;
import com.springboot.data.jpa.app.entity.CustomerEntity;
import com.springboot.data.jpa.app.entity.ItemEntity;
import com.springboot.data.jpa.app.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderJpaService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public UUID createOrders(UUID customerId, OrderRequest orderRequest) {
        CustomerEntity customerEntity = this.getCustomerEntity(customerId);
        OrderEntity orderEntity = this.toOrderEntity(new OrderEntity(), orderRequest);
        if (orderRequest.getItems() != null && !orderRequest.getItems().isEmpty()) {
            List<ItemEntity> itemEntities = this.toItemEntities(orderRequest.getItems());
            itemEntities.forEach(i -> i.setOrder(orderEntity));
            orderEntity.setItems(itemEntities);
        }
        return this.orderRepository.save(orderEntity).getId();
    }

    private OrderEntity toOrderEntity(OrderEntity orderEntity, OrderRequest orderRequest) {
        orderEntity.setOrderName(orderRequest.getOrderName());
        orderEntity.setOrderStatus(OrderStatus.CREATED);
        return orderEntity;
    }

    private CustomerEntity getCustomerEntity(UUID customerId) {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(customerId);
        if (customerEntity.isPresent()) {
            return customerEntity.get();
        }
        throw new RuntimeException("Customer Not Found!");
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

}
