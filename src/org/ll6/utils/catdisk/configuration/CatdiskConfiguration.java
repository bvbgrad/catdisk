package org.ll6.utils.catdisk.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
 
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ActiveProfiles({"dev", "prod"})
@ComponentScan(basePackages = {
	"org.ll6.utils.catdisk"
})

public class CatdiskConfiguration 
{
	private static final Logger logger = LogManager.getLogger();
	
//	@Autowired 
//	private DataSource dataSource;
//	 @Bean
//	    public AccountRepository accountRepository() {
//	        return new JdbcAccountRepository(dataSource);
//	    }	
	
    @Bean
    public ViewResolver viewResolver() {
    	logger.info("ViewResolver");
    	
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
    
    @Bean
    @Profile("prod")
    DataSource dataSourceProd() {
    	logger.info("dataSourceProd");
    	
    	DataSource dataSource = null;
    	JndiTemplate jndi = new JndiTemplate();
    	try {
    		dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/catdisk01");
    	} catch (NamingException e) {
    		logger.error("NamingException for java:comp/env/jdbc/catdisk01", e);
    	}
    	return dataSource;
    }
    
    @Bean
    @Profile("dev")
    DataSource dataSourceDev() {
    	logger.info("dataSourceDev");
        DataSource dataSource = null;
        JndiTemplate jndi = new JndiTemplate();
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/catdisk01-dev");
        } catch (NamingException e) {
            logger.error("NamingException for java:comp/env/jdbc/catdisk01-dev", e);
        }
        return dataSource;
    }

}
