package config;

import javax.activation.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class CatdiskTestConfiguration {
	private static final Logger logger = LogManager.getLogger();
    
//    @Bean(name = "persistenceXmlLocation")
//    public String persistenceXmlLocation() {
//    	logger.info("persistenceXmlLocation");
//        return "classpath:test-persistence.xml";
//    }
	
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//            .setType(EmbeddedDatabaseType.HSQL)
//            .addScript("classpath:schema.sql")
//            .addScript("classpath:test-data.sql")
//            .build();
//    }
	
//    @Bean
//    @Profile("prod")
//    DataSource dataSourceProd() {
//    	logger.info("dataSourceProd new");
//    	
//    	DataSource dataSource = null;
//    	JndiTemplate jndi = new JndiTemplate();
//    	try {
//    		dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/catdisk01");
//    	} catch (NamingException e) {
//    		logger.error("NamingException for java:comp/env/jdbc/catdisk01", e);
//    	}
//    	return dataSource;
//    }
//    
//    @Bean
//    @Profile("dev")
//    DataSource dataSourceDev() {
//    	logger.info("dataSourceDev new");
//    	DataSource dataSource = null;
//    	try {
//    		logger.info("envContext: Entering try block");
//        	Context initContext = new InitialContext();
//        	logger.info("envContext initContext: " + initContext.toString());
//        	Context envContext = (Context) initContext.lookup("java:comp/env");
//        	logger.info("envContext: " + envContext);
//        	 dataSource = (DataSource) envContext.lookup("jdbc/catdisk01-dev");
////            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/catdisk01-dev");
//        } catch (NamingException e) {
//            logger.error("NamingException for java:comp/env/jdbc/catdisk01-dev", e);
//        }
//        return dataSource;
//    }

}
