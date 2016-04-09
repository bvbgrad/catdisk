package org.ll6.utils.catdisk.controllers;

	import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

	@Controller
	@RequestMapping("/")
	public class CatdiskController {
		private static final Logger logger = LogManager.getLogger();

	    @RequestMapping(method = RequestMethod.GET)
	    public String sayHello(Locale locale, ModelMap model) {

	    	String sGreet = "Hello from Disk Catalog. Client locale = '" + 
	    			locale.toString() + "'";
	    	logger.info("sayHello: {}", sGreet);
	    	
	    	try {
	    		Process p = Runtime.getRuntime().exec("cmd /c vol e:");
	    		Scanner sc = new Scanner(p.getInputStream());    		
	    		while (sc.hasNext()) System.out.println(sc.nextLine());
	    		sc.close();
	    	}
	    	catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	}	

	    	model.addAttribute("greeting", sGreet);
	    	
	        return "catdisk";
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