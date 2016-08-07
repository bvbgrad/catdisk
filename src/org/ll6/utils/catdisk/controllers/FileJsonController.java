package org.ll6.utils.catdisk.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ll6.utils.catdisk.dao.DiskDao;
import org.ll6.utils.catdisk.dao.FileDataDao;
import org.ll6.utils.catdisk.entities.Disk;
import org.ll6.utils.catdisk.entities.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileJsonController {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private DiskDao diskDao;
	
	@Autowired
	private FileDataDao fileDataDao;
	
	@RequestMapping(value="/addDiskID/{idStart}/{idEnd}", method = RequestMethod.GET)
	public ResponseEntity<Integer> addDiskID
		(@PathVariable("idStart") long idStart, @PathVariable("idEnd") long idEnd)
//	public ResponseEntity<List<FileData>> addDiskID(@PathVariable("idStart") long idStart)
	{
		List<Disk> disks = new ArrayList<Disk>();
		List<FileData> files = new ArrayList<FileData>();
		int numFiles = 0;
//		long idEnd = idStart + 10;
		
		for (long id = idStart; id < idEnd + 1; id++) {
			
			Disk disk = diskDao.getDisk(id);
			logger.info("addDiskID for id: {} {}", id, disk);
			disks.add(disk);

			String volumeName = disk.getVolumeName();
			int copyNum = disk.getDiskCopyNum();
			logger.info("Get files for disk: '{}:{}'", volumeName, copyNum);
	        List<FileData> fileList = fileDataDao.getDiskFiles(volumeName, copyNum);
			numFiles = numFiles + fileList.size();
			System.out.println(fileList.size() + " files for Disk " +id);
			
			files.clear();
			for(FileData fileData: fileList) {
				fileData.setDiskID(id);
				fileDataDao.saveOrUpdate(fileData);
//				System.out.println("Disk " + id + ": " + "file: " + fileData );
				files.add(fileData);
			}
			
			System.out.println("Disk: " + disk.getDiskID() + " file list");
//			System.out.println(files);
		}
		
		return new ResponseEntity<Integer>(numFiles, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFile/{id}", method = RequestMethod.GET)
	public ResponseEntity<FileData> getFileData(@PathVariable("id") long id)
	{
		FileData file = fileDataDao.getFile(id);
		logger.info("getFileData for id: {} {}", id, file);
		
		return new ResponseEntity<FileData>(file, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFileRange/{start}/{stop}", method = RequestMethod.GET)
	public ResponseEntity<List<FileData>> getFileRange
		(@PathVariable("start") long start, @PathVariable("stop") long stop)
	{
		logger.info("Get range of files: {} to {}", start, stop);
		List<FileData> fileList = fileDataDao.getSomeFiles(start, stop);
		return new ResponseEntity<List<FileData>>(fileList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getDiskFiles/{volumeName}/{copyNum}", method = RequestMethod.GET)
	public ResponseEntity<List<FileData>> getDiskFiles
		(@PathVariable("volumeName") String volumeName, @PathVariable("copyNum") int copyNum)
	{
		logger.info("Get files for disk: '{}:{}'", volumeName, copyNum);
        List<FileData> fileList = fileDataDao.getDiskFiles(volumeName, copyNum);
		return new ResponseEntity<List<FileData>>(fileList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFiles/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<FileData>> getFiles(@PathVariable("id") long id)
    {
    	List<FileData> fileList = fileDataDao.getFiles(id);
    	logger.info("Get list of {} files for disk # {}", fileList.size(), id);
    	return new ResponseEntity<List<FileData>>(fileList, HttpStatus.OK);
    }

    @RequestMapping(value="/getFileCount", method = RequestMethod.GET)
    public long getFileCount()
    {
    	long fileCount = fileDataDao.getFileCount();
    	return fileCount;
    }
}
