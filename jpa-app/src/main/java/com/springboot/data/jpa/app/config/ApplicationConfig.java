package com.springboot.data.jpa.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.springboot.database.transaction.jpa.app"})
@EntityScan(basePackages = {"com.springboot.database.transaction.jpa"})
@ComponentScan("com.springboot.database.transaction.jpa.app")
public class ApplicationConfig {
}
