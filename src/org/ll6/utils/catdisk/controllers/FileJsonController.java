package org.ll6.utils.catdisk.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ll6.utils.catdisk.dao.FileDataDao;
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
	private FileDataDao fileDataDao;
	
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
	
    @RequestMapping(value="/getAllFiles", method = RequestMethod.GET)
    public ResponseEntity<List<FileData>> FileList(long id)
    {
    	List<FileData> fileList = fileDataDao.getAllFiles(id);
    	logger.info("Get list of all {} files", fileList.size());
    	return new ResponseEntity<List<FileData>>(fileList, HttpStatus.OK);
    }

    @RequestMapping(value="/getFileCount", method = RequestMethod.GET)
    public long getDiskCount()
    {
    	long fileCount = fileDataDao.getFileCount();
    	return fileCount;
    }
}
