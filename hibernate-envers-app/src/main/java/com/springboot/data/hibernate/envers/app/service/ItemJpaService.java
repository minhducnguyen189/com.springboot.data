package com.springboot.data.hibernate.envers.app.service;

import com.springboot.data.hibernate.envers.app.entity.CustomerEntity;
import com.springboot.data.hibernate.envers.app.entity.ItemEntity;
import com.springboot.data.hibernate.envers.app.entity.OrderEntity;
import com.springboot.data.hibernate.envers.app.model.request.ItemRequest;
import com.springboot.data.hibernate.envers.app.repository.CustomerRepository;
import com.springboot.data.hibernate.envers.app.repository.ItemRepository;
import com.springboot.data.hibernate.envers.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemJpaService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    private List<UUID> createItem(UUID customerId, UUID orderId, List<ItemRequest> items) {
        CustomerEntity customerEntity = this.getCustomerEntity(customerId);
        OrderEntity orderEntity = this.getOrderEntity(orderId);
        this.validateRelationship(customerEntity, orderId);
        List<ItemEntity> itemEntities = this.toItemEntities(items);
        itemEntities.forEach(i -> i.setOrder(orderEntity));
        List<ItemEntity> itemEntityResults = this.itemRepository.saveAll(itemEntities);
        return itemEntityResults.stream().map(ItemEntity::getId).collect(Collectors.toList());
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

    private CustomerEntity getCustomerEntity(UUID customerId) {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(customerId);
        if (customerEntity.isPresent()) {
            return customerEntity.get();
        }
        throw new RuntimeException("Customer Not Found!");
    }

    private OrderEntity getOrderEntity(UUID orderId) {
        Optional<OrderEntity> orderEntity = this.orderRepository.findById(orderId);
        if (orderEntity.isPresent()) {
            return orderEntity.get();
        }
        throw new RuntimeException("Order Not Found!");
    }

    private void validateRelationship(CustomerEntity customer, UUID orderId) {
        List<OrderEntity> orderEntities = customer.getOrders();
        if (!orderEntities.isEmpty()) {
            Optional<OrderEntity> existedOrderEntity = orderEntities.stream().filter(o -> orderId.equals(o.getId())).findFirst();
            if (existedOrderEntity.isPresent()) return;
        }
        throw new RuntimeException("No Relationship Between Customer and Order");
    }

}
