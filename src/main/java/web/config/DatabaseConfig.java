package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;


import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;



import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.User;


@Configuration
    @PropertySource({"classpath:db.properties"})
    @EnableTransactionManagement
    @ComponentScan({"web"})
    public class DatabaseConfig {
        @Autowired
        private Environment env;

        public DatabaseConfig() {
        }

        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(this.env.getProperty("db.driver"));
            dataSource.setUrl(this.env.getProperty("db.url"));
            dataSource.setUsername(this.env.getProperty("db.username"));
            dataSource.setPassword(this.env.getProperty("db.password"));
            return dataSource;
        }

        @Bean
        public LocalSessionFactoryBean getSessionFactory() {
            LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
            factoryBean.setDataSource(this.getDataSource());
            Properties props = new Properties();
            props.put("hibernate.show_sql", this.env.getProperty("hibernate.show_sql"));
            props.put("hibernate.hbm2ddl.auto", this.env.getProperty("hibernate.hbm2ddl.auto"));
            factoryBean.setHibernateProperties(props);
            factoryBean.setAnnotatedClasses(User.class);
            return factoryBean;
        }

        @Bean
        public HibernateTransactionManager getTransactionManager() {
            HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(this.getSessionFactory().getObject());
            return transactionManager;
        }
    }



