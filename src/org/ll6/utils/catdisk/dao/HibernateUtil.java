package org.ll6.utils.catdisk.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.ll6.utils.catdisk.entities.Disk;
import org.ll6.utils.catdisk.entities.FileData;

public class HibernateUtil {
	private static final Logger logger = LogManager.getLogger();
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		logger.debug("buildSessionFactory");
		
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Disk.class);
			configuration.addAnnotatedClass(FileData.class);
			return configuration
					.buildSessionFactory(new StandardServiceRegistryBuilder()
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
