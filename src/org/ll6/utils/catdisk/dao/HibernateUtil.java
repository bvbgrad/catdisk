package org.ll6.utils.catdisk.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.ll6.utils.catdisk.entities.Disk;
import org.ll6.utils.catdisk.entities.FileData;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("prod")
@Component("HibernateUtil")
public class HibernateUtil {
	private static final Logger logger = LogManager.getLogger();
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		logger.debug("buildSessionFactory");
		
		try {
			Configuration cfg = new Configuration()
//				.configure()
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
				.setProperty("hibernate.show_sql", "true")
			    .setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/catdisk01")
				.addAnnotatedClass(Disk.class)
				.addAnnotatedClass(FileData.class);
			return cfg.buildSessionFactory(new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties())
					.build());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("There was an error building the factory");
		}
	}
	
	public static SessionFactory getSessionFactory(){
		logger.debug("getSessionFactory");
		
		return sessionFactory;
	}
}
