package org.ll6.utils.catdisk.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.ll6.utils.catdisk.entities.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Component("diskDao")
@SuppressWarnings("unchecked")
public class DiskDao {
	private static final Logger logger = LogManager.getLogger();

//	@Autowired
//	private SessionFactory sessionFactory;
	public Session session() 
	{
//		return sessionFactory.getCurrentSession();
		return HibernateUtil.getSessionFactory().openSession();
	}

	public long addDisk(String volumeID, String serial, Integer copyID, 
			String descriptionTxt, String locationTxt,
			Timestamp createdOn)
	{
		
		Disk Disk = 
			new Disk();

		logger.info("addDisk: " + Disk);
		session().save(Disk);
		return Disk.getDiskID();  // Caller usually needs ID number

	}
	
	public long getDiskCount() 
	{
		long diskCount = (long) session().createQuery(
			"select count(*) from Disk")
			.uniqueResult();
		logger.info("getDiskCount : {}", diskCount);
		
		return diskCount;
	}
	
	public Disk getDisk(long id) {
		logger.info("getDisk for id: " + id);	
		Criteria crit = session().createCriteria(Disk.class);
		crit.add(Restrictions.idEq(id));
		return (Disk)crit.uniqueResult();
	}
	
	public List<Disk> getSomeDisks(long start, long end)
	{
		long idRange = end - start +1;
		logger.info("get " + idRange + " disks:  starting with: " + start);	
		Criteria crit = session().createCriteria(Disk.class);
		crit.add(Restrictions.gt("id", start - 1)); // have to offset index to get
		crit.add(Restrictions.lt("id", end + 1));	// the entire range requested
		return crit.list();
	}
	
	public List<Disk> getAllDisks() {
		logger.info("getAllDisks");	
		return session().createQuery("from Disk").list();
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

}
