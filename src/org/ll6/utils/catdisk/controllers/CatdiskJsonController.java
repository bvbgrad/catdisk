package org.ll6.utils.catdisk.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ll6.utils.catdisk.dao.DiskDao;
import org.ll6.utils.catdisk.entities.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatdiskJsonController {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private DiskDao diskDao;
	
	@RequestMapping(value="/get")
	public Map<String, Object> getData(Model model)
	{
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", "Message01");
		data.put("number", 1);
		logger.info("getData: {}", data);
		return data;
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

    @RequestMapping(value="/getDisks")
    public void DiskList()
    {
    	List<Disk> diskList = diskDao.getAllDisks();
    	System.out.println("Number of disks: " + diskList.size());
        for (int i=0; i<10; i++)
        {
        	System.out.println(diskList.get(i).toString());
        }
        
        System.out.println("Get range of disks");
        diskList = diskDao.getSomeDisks(50, 60);
        for (Disk disk : diskList)
        {
        	System.out.println(disk.toString());
        }
        
    	
    }

}
