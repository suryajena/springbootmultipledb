package org.surya;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "org.surya.domain.primary",
		entityManagerFactoryRef = "primaryEntityManager",
		transactionManagerRef = "primaryTransactionManager"
		)
public class PrimaryDBConfig
{
	@Autowired
	private Environment env;
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(primaryDataSource());
		em.setPackagesToScan(new String[] { "org.surya.domain.primary" });

		HibernateJpaVendorAdapter vendorAdapter	= new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		//properties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect",	env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix="datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	@Primary
	@Bean
	public PlatformTransactionManager primaryTransactionManager() {

		JpaTransactionManager transactionManager
		= new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
				primaryEntityManager().getObject());
		return transactionManager;
	}

	/*
	@Primary
	@Bean
	public DataSource userDataSource() {

		DriverManagerDataSource dataSource
		= new DriverManagerDataSource();
		dataSource.setDriverClassName(
				env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("user.jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));

		return dataSource;
	}*/



	/*@Bean
	@Primary
	@ConfigurationProperties(prefix="datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}




	@Bean("primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean companyEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(primaryDataSource())
				.packages("org.surya.domain.primary")
				.persistenceUnit("primary")
				.build();
	}

	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager primaryTransactionManager(
			@Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}*/
}
