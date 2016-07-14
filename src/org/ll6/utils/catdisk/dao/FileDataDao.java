package org.ll6.utils.catdisk.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
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
//		return sessionFactory.getCurrentSession();
		return HibernateUtil.getSessionFactory().openSession();
	}

	public long addFile(String diskID, String fileName,
			String filePath, Date DateCreated, int fileLength,
			Timestamp createdOn)
	{
		
		FileData fileData = 
			new FileData();

		logger.info("addFile: " + fileData);
		session().save(fileData);
		return fileData.getID();  // Caller usually needs ID number

	}
	
	public long getFileCount() 
	{
		long fileCount = (long) session().createQuery(
			"select count(*) from FileData")
			.uniqueResult();
		logger.info("getFileCount : {}", fileCount);
		
		return fileCount;
	}
	
	public FileData getFile(long id) {
		logger.info("getFile for id: " + id);	
		Criteria crit = session().createCriteria(FileData.class);
		crit.add(Restrictions.idEq(id));
		return (FileData)crit.uniqueResult();
	}
	
	public List<FileData> getSomeFiles(long start, long end)
	{
		long idRange = end - start +1;
		logger.info("get " + idRange + " files:  starting with: " + start);	
		Criteria crit = session().createCriteria(FileData.class);
		crit.add(Restrictions.gt("id", start - 1)); // have to offset index to get
		crit.add(Restrictions.lt("id", end + 1));	// the entire range requested
		return crit.list();
	}
	
	public List<FileData> getDiskFiles(String volumeName, int copyNum) {
		logger.info("get files for {} : {}: ", volumeName, copyNum);	
		Criteria crit = session().createCriteria(FileData.class);
		crit.add(Restrictions.eq("volumeName", volumeName));
		crit.add(Restrictions.eq("diskCopyNum", copyNum));	
		return crit.list();
	}

	public List<FileData> getFiles(long id) {
		logger.info("getFiles for disk # {}", id);	
		Criteria crit = session().createCriteria(FileData.class);
		crit.add(Restrictions.eq("diskID", id));
		return crit.list();
	}

	public void saveOrUpdate(FileData fileData) {
		logger.info("saveOrUpdate: " + fileData);	
		session().saveOrUpdate(fileData);
	}

	public boolean delete(int id) {
		logger.info("delete: " + id);	
		Query query = session().createQuery("delete from FileData where id=:id");
		query.setInteger("id", id);
		return query.executeUpdate() == 1;
	}

}
