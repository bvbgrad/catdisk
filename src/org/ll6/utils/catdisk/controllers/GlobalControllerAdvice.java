package org.ll6.utils.catdisk.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations=Controller.class)
public class GlobalControllerAdvice {
	private static final Logger logger = LogManager.getLogger();
	
	@ModelAttribute("currentDate")
	public String getCurrentDate(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		return formattedDate;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView myError(HttpServletRequest req, Exception e) {
	    ModelAndView mav = new ModelAndView();
		String sURL = req.getRequestURL().toString();
		logger.error("Exception error from URL {}: full stack trace follows:", sURL, e );
		
		mav.addObject("currentDate", new Date().toString());
		mav.addObject("sErrorMsg", e.toString());
		mav.addObject("url", sURL);
	    mav.setViewName("errorView");
	    return mav;
	  }

}
