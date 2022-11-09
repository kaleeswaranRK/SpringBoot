package com.klibrary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klibrary.service.Calculator;

@RestController
@RequestMapping("/calculation/")
//@PropertySource(value = {
//		"C:\\Users\\kalees\\Desktop\\TeleApps\\E-Library\\E-Library\\src\\main\\resources\\userNamePassord" })
public class Controller {
	@Autowired
	Calculator cal;
	@Autowired
	Environment env;
	String ans;
	@Value("${test}")
	String pathloc;
	@GetMapping(value = "operation")
	public ResponseEntity<?> testAPI(@RequestParam Map<String,String> req) throws Exception {
		System.out.println("TestCalculation Started");
		System.out.println(req);
		Logger logger = LogManager.getLogger("calcutation");
		logger.debug("user input : ",req);
		logger.info("user input : ",req);
		String operation = req.get("operation");
		try {
			int a = Integer.parseInt(req.get("a"));
			int b = Integer.parseInt(req.get("b"));
			ans = cal.prop(operation, a, b);
			if (ans.equals("Invalid input")) {
				req.put("Status", "Failiure");
				req.put("Answer", "Invalid input");
			}
			else {
				req.put("Status", "Success");
				req.put("Answer", ans);
			}
			logger.trace("user output : ",req);
			
		} 
		catch (Exception e) {
			req.put("Status", "Failiure");
			req.put("Answer", "Invalid input");
			System.out.println(e.getStackTrace());
			logger.error("Exception : ",e);
		}
		System.out.println("TestCalculation Ended");

		return new ResponseEntity<>(req, HttpStatus.OK);
	}
	@GetMapping(value = "testProperty")
	public ResponseEntity<?> testProperty() throws Exception {
		System.out.println("TestProperty Started");
		Properties property=new Properties();;
		try {
			File f= new File(pathloc);
			FileInputStream fin= new FileInputStream(f);
			property.load(fin);
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("TestProperty Ended");
		
		return new ResponseEntity<>(property, HttpStatus.OK);
	}

}
