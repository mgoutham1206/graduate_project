package edu.nwmissouri.geoapp.config;

import java.util.Properties;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableJpaRepositories("edu.nwmissouri.geoapp.repository")
@PropertySource(value = "classpath:application.properties")
public class DataSourceConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public DriverManagerDataSource dataSource(){
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty("packages_to_scan"));
		entityManagerFactoryBean.setJpaProperties(hibProperties());
		return entityManagerFactoryBean;
	}
	
	private Properties hibProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
/*		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
*/		properties.put("spring.jpa.hibernate.ddl-auto",environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		return properties;
		
	}
	
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
