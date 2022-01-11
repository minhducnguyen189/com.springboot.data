package com.springboot.data.root.app;

import com.springboot.data.jpa.app.annotation.EnableJpaTransactionApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJpaTransactionApp
@SpringBootApplication
public class RootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }

}
