package com.springboot.data.jdbc.app.service;

import com.springboot.data.jdbc.app.dao.CustomerDao;
import com.springboot.data.jdbc.app.model.request.CustomerRequest;
import com.springboot.data.jdbc.app.model.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomerJdbcService {


    @Autowired
    private CustomerDao customerDao;

    public UUID createCustomer(CustomerRequest customerRequest) {
        UUID customerId = this.customerDao.createCustomer(customerRequest);
        List<OrderRequest> orders = customerRequest.getOrders();
        if (orders != null && !orders.isEmpty()) {
            Map<UUID, OrderRequest> orderRequestMap = this.customerDao.createOrders(customerId, customerRequest.getOrders());
            for (Map.Entry<UUID, OrderRequest> map : orderRequestMap.entrySet()) {
                if (map.getValue().getItems() != null && !map.getValue().getItems().isEmpty()) {
                    this.customerDao.createItems(map.getKey(), map.getValue().getItems());
                }
            }
        }
        return customerId;
    }

}
