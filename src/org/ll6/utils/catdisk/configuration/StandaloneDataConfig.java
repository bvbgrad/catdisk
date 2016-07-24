package org.ll6.utils.catdisk.configuration;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class StandaloneDataConfig {

	@Bean
	public DataSource dataSource() throws Exception {
        InitialContext ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
    }
    
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//            .setType(EmbeddedDatabaseType.HSQL)
//            .addScript("classpath:com/bank/config/sql/schema.sql")
//            .addScript("classpath:com/bank/config/sql/test-data.sql")
//            .build();
//    }
}