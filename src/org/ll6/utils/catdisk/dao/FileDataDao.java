package org.ll6.utils.catdisk.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.ll6.utils.catdisk.entities.FileData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Component("fileDataDao")
@SuppressWarnings("unchecked")
public class FileDataDao {
	private static final Logger logger = LogManager.getLogger();

	public Session session() 
	{
//		return SessionFactory.getCurrentSession();
		return HibernateUtil.getSessionFactory().openSession();
//		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public long addFile(String diskID, String fileName,
			String filePath, Date DateCreated, int fileLength,
			Timestamp createdOn)
	{
		
		FileData fileData = new FileData();
		Session session = session();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.save(fileData);
			transaction.commit();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("addFile: close session");
				session.close();
		}
		logger.info("addFile: " + fileData);
		return fileData.getID();  // Caller usually needs ID number

	}
	
	public long getFileCount() 
	{
		long fileCount = 0;
		
		Session session = session();
		try {
			fileCount = (long) session.createQuery(
					"select count(*) from FileData")
					.uniqueResult();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getFileCount: close session");
				session.close();
		}
		logger.info("getFileCount : {}", fileCount);
		
		return fileCount;
	}
	
	public FileData getFile(long id) {
		logger.info("getFile for id: " + id);	

		FileData fileData = new FileData();
		Session session = session();
		try {
			Criteria crit = session.createCriteria(FileData.class);
			crit.add(Restrictions.idEq(id));
			fileData = (FileData)crit.uniqueResult();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getFile: close session");
				session.close();
		}
		
		return fileData;
	}
	
	public List<FileData> getSomeFiles(long start, long end)
	{
		long idRange = end - start +1;
		logger.info("get " + idRange + " files:  starting with: " + start);
		
		List<FileData> fileDataList = new ArrayList<FileData>();
		Session session = session();
		try {
			Criteria crit = session.createCriteria(FileData.class);
			crit.add(Restrictions.gt("id", start - 1)); // have to offset index to get
			crit.add(Restrictions.lt("id", end + 1));	// the entire range requested
			fileDataList = (List<FileData>)crit.uniqueResult();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getSomeFiles: close session");
				session.close();
		}
		return fileDataList;
	}
	
	public List<FileData> getDiskFiles(String volumeName, int copyNum) {
		logger.info("get files for {} : {}: ", volumeName, copyNum);	
		
		List<FileData> fileDataList = new ArrayList<FileData>();
		Session session = session();
		try {
			Criteria crit = session.createCriteria(FileData.class);
			crit.add(Restrictions.eq("volumeName", volumeName));
			crit.add(Restrictions.eq("diskCopyNum", copyNum));	
			fileDataList = (List<FileData>)crit.list();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getDiskFiles: close session");
				session.close();
		}
		return fileDataList;
	}

	public List<FileData> getFiles(long id) {
		logger.info("getFiles for disk # {}", id);	

		List<FileData> fileDataList = new ArrayList<FileData>();
		Session session = session();
		try {
			Criteria crit = session.createCriteria(FileData.class);
			crit.add(Restrictions.eq("diskID", id));
			fileDataList = (List<FileData>)crit.list();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getFiles: close session");
				session.close();
		}
		return fileDataList;
	}
	
	public int getNumberFilesForDisk(long id, String volumeName, int copyNum) {
		logger.info("getNumberFilesForDisk for disk # {} = {} : {}", id, volumeName, copyNum);	

		int numberFiles = 0;
		Session session = session();
		try {
			Criteria crit = session.createCriteria(FileData.class);
//			crit.add(Restrictions.eq("diskID", id));
			crit.add(Restrictions.eq("volumeName", volumeName));
			crit.add(Restrictions.eq("diskCopyNum", copyNum));	
			numberFiles =crit.list().size();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				logger.debug("getNumberFilesForDisk: close session");
				session.close();
		}
		return numberFiles;
	}

	public void saveOrUpdate(FileData fileData) {
		logger.debug("saveOrUpdate: " + fileData);
		
		Session session = session();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.saveOrUpdate(fileData);
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
			Query query = session.createQuery("delete from FileData where id=:id");
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
