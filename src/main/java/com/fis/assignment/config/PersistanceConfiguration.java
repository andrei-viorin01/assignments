package com.fis.assignment.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
* PostgreSQL DataSource configuration.
*
* @author Andrei Almasanu
*/

@Configuration
public class PersistanceConfiguration {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/fis-assignment");
        builder.username("username");
        builder.password("password");
        return builder.build();
    }
}
