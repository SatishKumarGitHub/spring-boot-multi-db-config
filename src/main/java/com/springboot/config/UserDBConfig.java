package com.springboot.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManager", basePackages = {
		"com.springboot.user.repository" }, transactionManagerRef="userTransactionManager")
public class UserDBConfig {

	@Bean(name = "userDatasource")
	@ConfigurationProperties(prefix = "spring.user.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "userEntityManager")
	public LocalContainerEntityManagerFactoryBean entityMangerBean(EntityManagerFactoryBuilder builder,
			@Qualifier("userDatasource") DataSource dataSource) {
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.springboot.user.model")
				.persistenceUnit("User").build();

	}

	@Bean("userTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("userEntityManager") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
}
