package com.springboot.data.jdbc.app.dao;

import com.springboot.data.jdbc.app.model.request.CustomerRequest;
import com.springboot.data.jdbc.app.model.request.ItemRequest;
import com.springboot.data.jdbc.app.model.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;


    public UUID createCustomer(CustomerRequest customerRequest) {
        String sqlCustomerQuery =
       "INSERT INTO customers(id, address, dob, email, full_name, gender, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        UUID uuid = this.getRandomUUID();
        this.jdbcTemplate.update(sqlCustomerQuery,
                uuid.toString(),
                customerRequest.getAddress(),
                customerRequest.getDob(),
                customerRequest.getEmail(),
                customerRequest.getFullName(),
                customerRequest.getGender().toString(),
                customerRequest.getPhone());
        return uuid;
    }

    public void createOrders(List<OrderRequest> orderRequestList) {
        final int size = orderRequestList.size();
        String sqlOrderQuery =
                         "INSERT INTO orders(                                "
                 +       "id,                                                " /*1*/
                 +       "created_date,                                      " /*2*/
                 +       "last_updated_date,                                 " /*3*/
                 +       "order_name,                                        " /*4*/
                 +       "order_status)                                      " /*5*/
                 +       "VALUES(                                            "
                 +       ":id,                                               " /*1*/
                 +       ":created_date,                                     " /*2*/
                 +       ":last_updated_date,                                " /*3*/
                 +       ":order_name,                                       " /*4*/
                 +       ":order_status)                                     " /*5*/
                 ;
        UUID uuid = this.getRandomUUID();
        List<SqlParameterSource> sqlParameterSources = new ArrayList<>(size);
        for (OrderRequest orderRequest: orderRequestList) {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            LocalDateTime now = LocalDateTime.now();
            mapSqlParameterSource.addValue("id", uuid);
            mapSqlParameterSource.addValue("created_date",now);
            mapSqlParameterSource.addValue("last_updated_date", now);
            mapSqlParameterSource.addValue("order_name", orderRequest.getOrderName());
            mapSqlParameterSource.addValue("order_status", orderRequest.getOrderStatus());
            sqlParameterSources.add(mapSqlParameterSource);
        }
        this.namedParameterJdbcOperations.batchUpdate(sqlOrderQuery, sqlParameterSources.toArray(new SqlParameterSource[size]));
    }

    public void createItems(List<ItemRequest> itemRequests) {
        final int size = itemRequests.size();
        String itemSqlQuery =
                "INSERT INTO orders(                                                "
                        +       "id,                                                " /*1*/
                        +       "item_name,                                         " /*2*/
                        +       "price,                                             " /*3*/
                        +       "quantity,                                          " /*4*/
                        +       "VALUES(                                             "
                        +       ":id,                                               " /*1*/
                        +       ":item_name,                                        " /*2*/
                        +       ":price,                                            " /*3*/
                        +       ":quantity)                                         " /*4*/
                        ;
        UUID uuid = this.getRandomUUID();
        List<SqlParameterSource> sqlParameterSources = new ArrayList<>(size);
        for (ItemRequest itemRequest: itemRequests) {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("id", uuid);
            mapSqlParameterSource.addValue("item_name", itemRequest.getItemName());
            mapSqlParameterSource.addValue("item_name", itemRequest.getItemName());
            mapSqlParameterSource.addValue("price", itemRequest.getPrice());
            mapSqlParameterSource.addValue("quantity", itemRequest.getQuantity());
            sqlParameterSources.add(mapSqlParameterSource);
        }
        this.namedParameterJdbcOperations.batchUpdate(itemSqlQuery, sqlParameterSources.toArray(new SqlParameterSource[size]));
    }


    private UUID getRandomUUID() {
        return UUID.randomUUID();
    }
}



