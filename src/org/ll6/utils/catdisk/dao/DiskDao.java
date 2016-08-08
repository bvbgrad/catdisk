package org.ll6.utils.catdisk.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.ll6.utils.catdisk.entities.Disk;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Component("diskDao")
@SuppressWarnings("unchecked")
public class DiskDao {
	private static final Logger logger = LogManager.getLogger();

	public Session session() 
	{
		return HibernateUtil.getSessionFactory().openSession();
	}

	public long addDisk(String volumeName, String diskSerialNum, Date diskDate, 
			String descriptionTxt, String locationTxt)
	{
		
		Disk disk =new Disk(volumeName, diskSerialNum, diskDate, 
				descriptionTxt, locationTxt);
		logger.info("addDisk: " + disk);
		saveOrUpdate(disk);
		return disk.getDiskID();  // Caller usually needs ID number

	}
	
	public long getDiskCount() 
	{
		long diskCount = 0;
		
		Session session = session();
		try {
			diskCount = (long) session.createQuery(
					"select count(*) from Disk")
					.uniqueResult();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getDiskCount: close session");
				session.close();
		}
		logger.info("getDiskCount : {}", diskCount);
		return diskCount;
		
	}
	
	public Disk getDisk(long id) {
		logger.info("getDisk for id: " + id);	
		
		Disk disk = null;
		Session session = session();
		try {
			Criteria crit = session.createCriteria(Disk.class);
			crit.add(Restrictions.idEq(id));
			disk = (Disk)crit.uniqueResult();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getDisk: close session");
				session.close();
		}
		return disk;
	}
	
	public List<Disk> getSomeDisks(long start, long end)
	{
		long idRange = end - start +1;
		logger.info("get " + idRange + " disks:  starting with: " + start);
		
		List<Disk> disks = new ArrayList<Disk>();
		Session session = session();
		try {
			Criteria crit = session.createCriteria(Disk.class);
			crit.add(Restrictions.gt("id", start - 1)); // have to offset index to get
			crit.add(Restrictions.lt("id", end + 1));	// the entire range requested
			disks = crit.list();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getSomeDisks: close session");
				session.close();
		}
		return disks;
	}
	
	public List<Disk> getAllDisks() {
		logger.info("getAllDisks");	
		List<Disk> disks = new ArrayList<Disk>();
		Session session = session();
		try {
			disks = session.createQuery("from Disk").list();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getAllDisks: close session");
				session.close();
		}
		return disks;
	}

	public void saveOrUpdate(Disk disk) {
		logger.info("saveOrUpdate: " + disk);	
		
		Session session = session();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.saveOrUpdate(disk);
			transaction.commit();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("saveOrUpdate: close session");
				session.close();
		}
	}

	public boolean delete(int id) {
		logger.info("delete: " + id);	

		Boolean success = false;
		Session session = session();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query query = session.createQuery("delete from Disk where id=:id");
			query.setInteger("id", id);
			success = query.executeUpdate() == 1;
			transaction.commit();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("delete: close session");
				session.close();
		}
		return success;
	}

}
