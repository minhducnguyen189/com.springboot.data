package com.springboot.data.jdbc.app.service;

import com.springboot.data.jdbc.app.dao.CustomerDao;
import com.springboot.data.jdbc.app.model.request.CustomerRequest;
import com.springboot.data.jdbc.app.model.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerJdbcService {


    @Autowired
    private CustomerDao customerDao;

    public UUID createCustomer(CustomerRequest customerRequest) {
        UUID customerId = this.customerDao.createCustomer(customerRequest);
//        List<OrderRequest> orders = customerRequest.getOrders();
//        if (orders != null && !orders.isEmpty()) {
//            this.customerDao.createOrders(customerRequest.getOrders());
//            for (OrderRequest order: orders) {
//                if (order.getItems() != null && !order.getItems().isEmpty()) {
//                    this.customerDao.createItems(order.getItems());
//                }
//            }
//        }
        return customerId;
    }

}
