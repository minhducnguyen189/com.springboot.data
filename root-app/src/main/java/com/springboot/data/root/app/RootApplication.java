package com.springboot.data.root.app;

import com.springboot.data.jdbc.app.annotation.EnableJdbcApp;
import com.springboot.data.jpa.app.annotation.EnableJpaApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJdbcApp
@EnableJpaApp
@SpringBootApplication
public class RootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }

}
