package com.lol;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class PickRecommenderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PickRecommenderApplication.class, args);
	}
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataSource dataSource;
	
	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	    try {
	    	sessionFactory.getCurrentSession();
	    } catch (HibernateException e) {
	    	sessionFactory.openSession();
	    }
	    return sessionFactory;
	}

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        tm.setDataSource(dataSource);
        return tm;
    }
}
