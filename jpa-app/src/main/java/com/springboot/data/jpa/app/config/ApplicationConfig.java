package com.springboot.data.jpa.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.springboot.data.jpa.app"})
@EntityScan(basePackages = {"com.springboot.data.jpa.app"})
@ComponentScan("com.springboot.data.jpa.app")
public class ApplicationConfig {
}
