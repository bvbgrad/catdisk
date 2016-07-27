package config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiTemplate;
 
@Configuration
public class CatdiskTestConfiguration 
{
	private static final Logger logger = LogManager.getLogger();
	
//	@Autowired 
//	private DataSource dataSource;
//	 @Bean
//	    public AccountRepository accountRepository() {
//	        return new JdbcAccountRepository(dataSource);
//	    }	
	
    @Bean
    @Profile("prod")
    DataSource dataSourceProd() {
    	logger.info("dataSourceProd new");
    	
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
    	logger.info("dataSourceDev new");
    	DataSource dataSource = null;
    	try {
    		logger.info("envContext: Entering try block");
        	Context initContext = new InitialContext();
        	logger.info("envContext initContext: " + initContext.toString());
        	Context envContext = (Context) initContext.lookup("java:comp/env");
        	logger.info("envContext: " + envContext);
        	 dataSource = (DataSource) envContext.lookup("jdbc/catdisk01-dev");
//            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/catdisk01-dev");
        } catch (NamingException e) {
            logger.error("NamingException for java:comp/env/jdbc/catdisk01-dev", e);
        }
        return dataSource;
    }

}
