package com.springboot.data.jdbc.app.application;

import com.springboot.data.jdbc.app.api.CustomerJDBCApi;
import com.springboot.data.jdbc.app.service.CustomerJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerJDBCImpl implements CustomerJDBCApi {

    @Autowired
    private CustomerJdbcService customerJdbcService;

}
