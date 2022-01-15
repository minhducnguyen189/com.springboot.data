package com.springboot.data.jdbc.app.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("com.springboot.data.jdbc.app")
public class ApplicationConfig {

    @Autowired
    private Environment env;

    @Bean("dataSource")
    public DataSource getDataSource() {

        final String sqlInitQuery = "SHOW PROCESSLIST;";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driverClassName")));
        basicDataSource.setUrl(env.getProperty("spring.datasource.url"));
        basicDataSource.setUsername(env.getProperty("spring.datasource.username"));
        basicDataSource.setPassword(env.getProperty("spring.datasource.password"));
        basicDataSource.setInitialSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.datasource.initialSize"))));
        basicDataSource.setMinIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.datasource.minIdle"))));
        basicDataSource.setMaxIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.datasource.maxIdle"))));
        basicDataSource.setMaxWait(Long.parseLong(Objects.requireNonNull(env.getProperty("spring.datasource.maxWait"))));
        basicDataSource.setMaxActive(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.datasource.maxActive"))));
        return basicDataSource;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        jdbcTemplate.setLazyInit(false);
        return jdbcTemplate;
    }
}
