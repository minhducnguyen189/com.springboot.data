package com.springboot.data.jpa.multi.datasource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
/**
 use this anotation to define the base package repository for secondary data source,
 the EntityFactory and the transactionManager
 **/
@EnableJpaRepositories(basePackages = "com.springboot.data.jpa.multi.datasource.repository.secondary",
        entityManagerFactoryRef = "secondaryEntityManager",
        transactionManagerRef = "secondaryTransactionManager")
public class SecondarySchemaConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSourceConfiguration() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.second-datasource.driverClassName")));
        dataSource.setUrl(env.getProperty("spring.second-datasource.url"));
        dataSource.setUsername(env.getProperty("spring.second-datasource.username"));
        dataSource.setPassword(env.getProperty("spring.second-datasource.password"));
        return dataSource;
    }

    @Bean(name = "secondaryEntityManager")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(secondaryDataSourceConfiguration());
        /**
         this is the package which contains entities of primary datasource
         **/
        em.setPackagesToScan("com.springboot.data.jpa.multi.datasource.entity.secondary");
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(jpaVendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(secondaryEntityManager().getObject());
        return transactionManager;
    }

}
