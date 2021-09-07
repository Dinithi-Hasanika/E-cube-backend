package ecube.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import ecube.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("ecube")
public class DemoAppConfig {

    @Bean
    public DataSource myDataSource() {

        // create connection pool
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            myDataSource.setDriverClass("com.mysql.jdbc.Driver");
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // for sanity's sake, let's log url and user ... just to make sure we are reading the data

        // set database connection props
        myDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ecube?useSSL=false");
        myDataSource.setUser("springstudent");
        myDataSource.setPassword("springstudent");

        // set connection pool props
        myDataSource.setInitialPoolSize(5);
        myDataSource.setMinPoolSize(5);
        myDataSource.setMaxPoolSize(20);
        myDataSource.setMaxIdleTime(3000);

        return myDataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {

        org.hibernate.cfg.Configuration con = new org.hibernate.cfg.Configuration().configure()
                .addAnnotatedClass( User.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

        SessionFactory sf = con.buildSessionFactory(reg);
        return sf;
    }

    //	@Bean
    //	@Autowired
    //	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    //		HibernateTransactionManager txManager = new HibernateTransactionManager();
    //		txManager.setSessionFactory(sessionFactory);
    //
    //		return txManager;
    //	}

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(myDataSource());
    }

}

