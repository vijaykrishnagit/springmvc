package com.revision.javabasedmvc;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.apache.commons.dbcp2.BasicDataSource;

@EnableWebMvc
@Configuration
@ComponentScan("com.revision.javabasedmvc")
@EnableTransactionManagement
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		
		BasicDataSource basicDatasource = new BasicDataSource();
		basicDatasource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDatasource.setUsername("root");
		basicDatasource.setPassword("wtf1234$");
		basicDatasource.setUrl("jdbc:mysql://localhost:3306/promotion");
		
		return basicDatasource;
		
		
	}
	
	public Properties getHibernateProperties(){
		Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	properties.put("hibernate.jdbc.batch_size", 3);
		return properties;
	}
	
	
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder lsfb = new LocalSessionFactoryBuilder(dataSource);
		lsfb.addProperties(getHibernateProperties());
		lsfb.addAnnotatedClass(Employee.class);
		lsfb.addAnnotatedClass(Laptop.class);
		return lsfb.buildSessionFactory();		
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

}
