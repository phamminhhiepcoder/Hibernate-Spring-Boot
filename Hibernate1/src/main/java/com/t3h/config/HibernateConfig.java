package com.t3h.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration // đánh dấu cho spring boot biết đây là file config cần để tạo bean trong bean container
@PropertySource("classpath:application.properties") // chỉ định file cấu hình để đọc trong đối tượng environment
@EnableTransactionManagement // khởi động transaction của hibernate
public class HibernateConfig {

    private final Environment environment; // là file cấu hình được chỉ định tại dòng 20

    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean // giúp tạo đối tượng session factory trong bean container( bean tương tự như việc chúng ta new Class())
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(getProperties());

        sessionFactoryBean.setPackagesToScan(new String[]{"com.t3h.entity"}); // chỉ định để hibernate biết đâu là package chứa các class Entity(ORM)

        return sessionFactoryBean;

    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setDriverClassName(environment.getProperty("driverClassName"));
        dataSource.setUsername(environment.getProperty("user"));
        dataSource.setPassword(environment.getProperty("password"));
        return dataSource;
    }

    private Properties getProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto")); // cấu hình tự động tạo ra table dựa vào entity
        return properties;
    }

    @Bean // giúp tạo bean HibernateTransactionManager trong bean container
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;
    }


}
