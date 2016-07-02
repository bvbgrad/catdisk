package org.ll6.utils.catdisk.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ll6.utils.catdisk.dao.DiskDao;
import org.ll6.utils.catdisk.entities.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatdiskJsonController {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private DiskDao diskDao;
	
//	@RequestMapping(value="/getDisk/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getDisk/{id}", method = RequestMethod.GET)
	public ResponseEntity<Disk> getDisk(@PathVariable("id") long id)
	{
		Disk disk = diskDao.getDisk(id);
		logger.info("getData for id: {} {}", id, disk);
		
		return new ResponseEntity<Disk>(disk, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getDiskRange/{start}/{stop}", method = RequestMethod.GET)
	public ResponseEntity<List<Disk>> getDiskRange
		(@PathVariable("start") long start, @PathVariable("stop") long stop)
	{
		logger.info("Get range of disks: {} to {}", start, stop);
        List<Disk> diskList = diskDao.getSomeDisks(start, stop);
        
        for (Disk disk : diskList)
        {
        	System.out.println(disk.toString());
        }
		
		return new ResponseEntity<List<Disk>>(diskList, HttpStatus.OK);
	}
	
    @RequestMapping(value="/getAllDisks", method = RequestMethod.GET)
    public ResponseEntity<List<Disk>> DiskList()
    {
    	List<Disk> diskList = diskDao.getAllDisks();
    	logger.info("Get list of all {} disks", diskList.size());
    	return new ResponseEntity<List<Disk>>(diskList, HttpStatus.OK);
    }
	
    @RequestMapping(value="/getVol")
    public String getVolumeData()
    {
    	String diskName = "";
    	String diskSerialNumber = "";
    	try {
    		Process p = Runtime.getRuntime().exec("cmd /c vol e:");
    		Scanner sc = new Scanner(p.getInputStream());
    		if (sc.hasNext()) {
    			diskName = sc.nextLine();
    			diskName = diskName.substring(22);
    		}
    		if (sc.hasNext()) {
    			diskSerialNumber = sc.nextLine();
    			diskSerialNumber = diskSerialNumber.substring(25);
    		}
    		sc.close();
    		
    		if (diskName == "" && diskSerialNumber == "")
    		{
    			diskName = "No disk";
    			System.out.println(diskName);
    		} else {
    			System.out.println("Disk Name: " + diskName + "\nSerial: " + diskSerialNumber);	    			
    		}

    	}
    	catch (IOException e) {
    		System.out.println(e.getMessage());
    	}	
        String data = diskName + ":" + diskSerialNumber;
    	logger.info("getData: {}", data);
        return data;
    }	


}
