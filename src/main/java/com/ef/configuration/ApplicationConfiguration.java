package com.ef.configuration;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.ef"})
@PropertySource("classpath:datasource.properties")
public class ApplicationConfiguration {

    @Autowired
    private Environment environment;

    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
        basicDataSource.setUrl(environment.getProperty("datasource.url"));
        basicDataSource.setUsername(environment.getProperty("datasource.username"));
        basicDataSource.setPassword(environment.getProperty("datasource.password"));
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
