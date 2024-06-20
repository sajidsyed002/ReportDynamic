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
        basePackages = {"com.report.config.repository"},
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager"
)
public class H2DatasourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.h2")
    public DataSource h2DataSource() {
        return h2DataSourceProperties()
                .initializeDataSourceBuilder()
                //.type(HikariDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate h2JdbcTemplate(@Qualifier("h2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(h2DataSource())
                .packages(ReportConfig.class, ColumnConfig.class, ColumnMetadataConfig.class, MetadataConfig.class, HedgeData.class,ScheduleConfig.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager h2TransactionManager(
            final @Qualifier("h2EntityManagerFactory") LocalContainerEntityManagerFactoryBean h2EntityManagerFactory) {
        return new JpaTransactionManager(h2EntityManagerFactory.getObject());
    }
}
