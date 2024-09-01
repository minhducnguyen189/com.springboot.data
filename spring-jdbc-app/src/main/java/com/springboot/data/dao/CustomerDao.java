package com.springboot.data.dao;

import com.springboot.data.model.OrderStatus;
import com.springboot.data.model.request.CustomerRequest;
import com.springboot.data.model.request.ItemRequest;
import com.springboot.data.model.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class CustomerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;


    public UUID createCustomer(CustomerRequest customerRequest) {
        String sqlCustomerQuery =
                "INSERT INTO customers(                          "
                        +          "id,                                             "   /*1*/
                        +          "address,                                        "   /*2*/
                        +          "dob,                                            "   /*3*/
                        +          "email,                                          "   /*4*/
                        +          "full_name,                                      "   /*5*/
                        +          "gender,                                         "   /*6*/
                        +          "phone)                                          "   /*7*/
                        +          "VALUES (?, ?, ?, ?, ?, ?, ?)                    "
                ;
        UUID uuid = this.getRandomUUID();
        this.jdbcTemplate.update(sqlCustomerQuery,
                uuid,
                customerRequest.getAddress(),
                customerRequest.getDob(),
                customerRequest.getEmail(),
                customerRequest.getFullName(),
                customerRequest.getGender().toString(),
                customerRequest.getPhone());
        return uuid;
    }

    public Map<UUID, OrderRequest> createOrders(UUID customerId, List<OrderRequest> orderRequestList) {
        Map<UUID, OrderRequest> map = new HashMap<>();
        final int size = orderRequestList.size();
        String sqlOrderQuery =
                "INSERT INTO orders(                                "
                        +       "id,                                                " /*1*/
                        +       "created_date,                                      " /*2*/
                        +       "last_updated_date,                                 " /*3*/
                        +       "order_name,                                        " /*4*/
                        +       "order_status,                                      " /*5*/
                        +       "customer_id)                                       " /*6*/
                        +       "VALUES(                                            "
                        +       ":id,                                               " /*1*/
                        +       ":created_date,                                     " /*2*/
                        +       ":last_updated_date,                                " /*3*/
                        +       ":order_name,                                       " /*4*/
                        +       ":order_status,                                     " /*5*/
                        +       ":customer_id)                                      " /*6*/
                ;
        List<SqlParameterSource> sqlParameterSources = new ArrayList<>(size);
        for (OrderRequest orderRequest: orderRequestList) {
            UUID uuid = this.getRandomUUID();
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            LocalDateTime now = LocalDateTime.now();
            mapSqlParameterSource.addValue("id", uuid);
            mapSqlParameterSource.addValue("created_date",now);
            mapSqlParameterSource.addValue("last_updated_date", now);
            mapSqlParameterSource.addValue("order_name", orderRequest.getOrderName());
            mapSqlParameterSource.addValue("order_status", OrderStatus.CREATED.toString());
            mapSqlParameterSource.addValue("customer_id", customerId);
            sqlParameterSources.add(mapSqlParameterSource);
            map.put(uuid, orderRequest);
        }
        this.namedParameterJdbcOperations.batchUpdate(sqlOrderQuery, sqlParameterSources.toArray(new SqlParameterSource[size]));
        return map;
    }

    public void createItems(UUID orderId, List<ItemRequest> itemRequests) {
        final int size = itemRequests.size();
        String itemSqlQuery =
                "INSERT INTO items(                                 "
                        +       "id,                                                " /*1*/
                        +       "item_name,                                         " /*2*/
                        +       "price,                                             " /*3*/
                        +       "quantity,                                          " /*4*/
                        +       "order_id)                                          " /*5*/
                        +       "VALUES(                                            "
                        +       ":id,                                               " /*1*/
                        +       ":item_name,                                        " /*2*/
                        +       ":price,                                            " /*3*/
                        +       ":quantity,                                         " /*4*/
                        +       ":order_id)                                         " /*5*/
                ;
        UUID uuid = this.getRandomUUID();
        List<SqlParameterSource> sqlParameterSources = new ArrayList<>(size);
        for (ItemRequest itemRequest: itemRequests) {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("id", uuid);
            mapSqlParameterSource.addValue("item_name", itemRequest.getItemName());
            mapSqlParameterSource.addValue("price", itemRequest.getPrice());
            mapSqlParameterSource.addValue("quantity", itemRequest.getQuantity());
            mapSqlParameterSource.addValue("order_id", orderId);
            sqlParameterSources.add(mapSqlParameterSource);
        }
        this.namedParameterJdbcOperations.batchUpdate(itemSqlQuery, sqlParameterSources.toArray(new SqlParameterSource[size]));
    }

    private UUID getRandomUUID() {
        return UUID.randomUUID();
    }
}
