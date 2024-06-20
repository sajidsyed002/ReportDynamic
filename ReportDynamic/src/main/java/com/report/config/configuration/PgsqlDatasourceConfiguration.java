package com.report.config.configuration;

import com.report.config.entity.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.report.config.repository_pgsql"},
        entityManagerFactoryRef = "pgsqlEntityManagerFactory",
        transactionManagerRef = "pgsqlTransactionManager"
)
public class PgsqlDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.pgsql")
    public DataSourceProperties pgsqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.pgsql")
    public DataSource pgsqlDataSource() {
        return pgsqlDataSourceProperties()
                .initializeDataSourceBuilder()
               // .type(BasicDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate pgsqlJdbcTemplate(@Qualifier("pgsqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "pgsqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pgsqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pgsqlDataSource())
                .packages("com.report.config.entity_pgsql")
                .build();
    }
    @Bean
    public PlatformTransactionManager pgsqlTransactionManager(
            final @Qualifier("pgsqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean pgsqlEntityManagerFactory) {
        return new JpaTransactionManager(pgsqlEntityManagerFactory.getObject());
    }
}