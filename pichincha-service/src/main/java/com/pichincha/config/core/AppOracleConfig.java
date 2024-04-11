package com.pichincha.config.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.Serializable;

/**
 * AppOracleConfig to run spring boot app.
 *
 * @author cfreire on 2022/08/31.
 * @version 1.0.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "oracleEntityManagerFactory",
        transactionManagerRef = "oracleTransactionManager",
        basePackages = {"com.pichincha.oracle"}
)
public class AppOracleConfig implements Serializable {

    private static final long serialVersionUID = -4580567702999656285L;

    /**
     * oracleDataSource
     *
     * @return
     */
    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * oracleEntityManagerFactory
     *
     * @param builder
     * @param oracleDataSource
     * @return
     */
    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("oracleDataSource") DataSource oracleDataSource) {
        return builder
                .dataSource(oracleDataSource)
                .packages("com.pichincha.oracle")
                .build();
    }

    /**
     * oracleTransactionManager
     *
     * @param oracleEntityManagerFactory
     * @return
     */
    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager oracleTransactionManager(
            @Qualifier("oracleEntityManagerFactory") EntityManagerFactory oracleEntityManagerFactory) {
        return new JpaTransactionManager(oracleEntityManagerFactory);
    }
}
