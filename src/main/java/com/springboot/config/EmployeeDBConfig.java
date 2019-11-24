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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "employeeEntityManager", basePackages = {
		"com.springboot.employee.repository" },transactionManagerRef="employeeTransactionManager")
public class EmployeeDBConfig {

	
	@Primary
	@Bean(name = "employeeDataSource")
	@ConfigurationProperties(prefix = "spring.employee.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean("employeeEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("employeeDataSource") DataSource dataSource) {
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");

		return builder.dataSource(dataSource).properties(properties).packages("com.springboot.employee.model")
				.persistenceUnit("Employee").build();

	}
	
	@Primary
	@Bean(name="employeeTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("employeeEntityManager")EntityManagerFactory factory){
		return new JpaTransactionManager(factory);
	}


}
