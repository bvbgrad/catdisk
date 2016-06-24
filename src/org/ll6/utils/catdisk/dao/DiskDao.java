package org.ll6.utils.catdisk.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.ll6.utils.catdisk.dao.entities.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("diskDao")
@SuppressWarnings("unchecked")
public class DiskDao {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private SessionFactory sessionFactory;
	public Session session() 
	{
		return sessionFactory.getCurrentSession();
	}

	public int addDisk(String volumeID, String serial, Integer copyID, 
			String descriptionTxt, String locationTxt,
			Timestamp createdOn)
	{
		
		Disk Disk = 
			new Disk(volumeID, serial, copyID, descriptionTxt, locationTxt, createdOn);

		logger.info("addDisk: " + Disk);
		session().save(Disk);
		return Disk.getId();  // Caller usually needs ID number

	}
	
	public List<Disk> getAllDisks() {
		logger.info("getAllDisks");	
		return session().createQuery("from Disk").list();
	}

	public List<Disk> getDisks(int id) {
		logger.info("getDisk number: " + id);	
		Criteria crit = session().createCriteria(Disk.class);
		crit.add(Restrictions.eq("id", id));
		return crit.list();
	}

	public void saveOrUpdate(Disk Disk) {
		logger.info("saveOrUpdate: " + Disk);	
		session().saveOrUpdate(Disk);
	}

	public boolean delete(int id) {
		logger.info("delete: " + id);	
		Query query = session().createQuery("delete from Disk where id=:id");
		query.setInteger("id", id);
		return query.executeUpdate() == 1;
	}

	public Disk getDisk(int id) {
		logger.info("getDisk for id: " + id);	
		Criteria crit = session().createCriteria(Disk.class);
		crit.add(Restrictions.idEq(id));
		return (Disk)crit.uniqueResult();
	}
	
}
