package net.codejava.customer.config;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
//import org.hibernate.cache.ehcache.internal.SingletonEhcacheRegionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableJpaRepositories(basePackages = {"net.codejava.customer"})
public class JpaConfig {
    @Bean
    public DataSource dataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root123");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:33066/salesdb");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setMinimumIdle(0);
        dataSource.setMaximumPoolSize(20);
        dataSource.setConnectionTimeout(30000);
        dataSource.setRegisterMbeans(true);
        return dataSource;
    }
    @Bean
    public Properties hibernateProps() {
        final Properties props = new Properties();
        props.setProperty(Environment.DIALECT, MySQLDialect.class.getName());
        props.setProperty(Environment.SHOW_SQL, Boolean.FALSE.toString());
        props.setProperty(Environment.FORMAT_SQL, Boolean.TRUE.toString());
        //props.setProperty(Environment.JDBC_TIME_ZONE, "UTC");
        //props.setProperty(Environment.USE_SECOND_LEVEL_CACHE, Boolean.TRUE.toString());
        //props.setProperty(Environment.USE_QUERY_CACHE, Boolean.TRUE.toString());
        //props.setProperty(Environment.CACHE_REGION_FACTORY, SingletonEhcacheRegionFactory.class.getName());
        //props.setProperty(Environment.CACHE_PROVIDER_CONFIG, "ehcache.xml");
        return props;
    }
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("entityManagerFactory");
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("net.codejava.customer");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(hibernateProps());
        emf.afterPropertiesSet();
        return emf.getNativeEntityManagerFactory();
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

/*
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"net.codejava.customer"})
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("SalesDB");

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}
*/
