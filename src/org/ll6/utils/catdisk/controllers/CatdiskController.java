package org.ll6.utils.catdisk.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

	@Controller
	public class CatdiskController {
		private static final Logger logger = LogManager.getLogger();

	    @RequestMapping(value = "/", method = RequestMethod.GET)
	    public String sayHello(Locale locale, ModelMap model) {

	    	String sGreet = "Hello from Disk Catalog. Client locale = '" + 
	    			locale.toString() + "'";
	    	logger.info("sayHello: {}", sGreet);
	    	
	    	String sDiskName = "";
	    	String sDiskSerialNumber = "";
	    	try {
	    		Process p = Runtime.getRuntime().exec("cmd /c vol e:");
	    		Scanner sc = new Scanner(p.getInputStream());
	    		if (sc.hasNext()) {
	    			sDiskName = sc.nextLine();
	    			sDiskName = sDiskName.substring(22);
	    		}
	    		if (sc.hasNext()) {
	    			sDiskSerialNumber = sc.nextLine();
	    			sDiskSerialNumber = sDiskSerialNumber.substring(25);
	    		}
	    		sc.close();
	    		
	    		if (sDiskName == "" && sDiskSerialNumber == "") {
	    			System.out.println("No disk information");
	    		} else {
	    			System.out.println("Disk Name: " + sDiskName + "\nSerial: " + sDiskSerialNumber);	    			
	    		}

	    	}
	    	catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	}	

	    	model.addAttribute("greeting", sGreet);
	    	model.addAttribute("diskVolName", sDiskName);
	    	model.addAttribute("diskSerialNumber", sDiskSerialNumber);
	    	
	        return "catdisk";
	    }
	    
	    @RequestMapping(value = "/scan")
	    public String scanDisk() {
	    	
//	    	int nFiles = 0;
//	    	String sPattern = "*.java";
	    	Boolean bPrint = false;
	    	File dir = new File("/e:"); 
	    	
//	    	Path path = getTargetDir();
	    	
//	    	File dir = path.toFile();
	    	walkin(dir, bPrint);
	    	
//	    	CatDiskScan catDiskScan = new CatDiskScan();
//			System.out.println("Step 4");

//			catDiskScan.scanDisk();  // immediately invoke primary method
			System.out.println("Step controller step 5");

	        return "catdisk";
	    }
	    
	    public Path getTargetDir() {
			final String sMname = "getTargetDir";
			logger.info(sMname);
	    	
			JFileChooser fileChooser = new JFileChooser("\\E:");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setApproveButtonText("Select directory");

			logger.info("getTargetDir1");
			Path pathTargetDir = null;
			logger.info("getTargetDir2");
			int showOpenDialog = fileChooser.showOpenDialog(null);
			logger.info("getTargetDir3");
			if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
				pathTargetDir = fileChooser.getSelectedFile().toPath();
			} else {  // null return means file chooser action was cancelled
//				log.info("No directory selected");
			}

			logger.info("getTargetDirv exit");
//			log.exiting(CLASS_NAME, sMname, pathTargetDir);
			return pathTargetDir;  
		}
	    
	    private void walkin(File dir, boolean bPrint) {
	    	File listFile[] = dir.listFiles();
	         if (listFile != null) {
	             for (int i=0; i<listFile.length; i++) {
	                 if (listFile[i].isDirectory()) {
                         System.out.println(listFile[i].getPath());
	                     walkin(listFile[i], bPrint);
	                 } else {
	                     if (bPrint) {
//	                    	 if (listFile[i].getName().endsWith(pattern)) {
	                         System.out.println(listFile[i].getPath());
	                     }
	                 }
	             }
	         }
	    }
	    
	    @RequestMapping(value = "/null")
	    public void nullPointerError() {
	    	throw new NullPointerException();
	    }
	 
	    @RequestMapping(value = "/sql")
	    public void dataError() throws SQLException {
	    	throw new SQLException();
	    }

	}