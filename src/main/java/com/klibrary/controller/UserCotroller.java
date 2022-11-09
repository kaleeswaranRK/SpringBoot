package com.klibrary.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usercontrol/")
public class UserCotroller {

	@GetMapping(value="user")
	public ResponseEntity<?> userPass(@RequestParam Map<String,String> user){
		Logger logger = LogManager.getLogger("UserCotroller");
		try {
			System.out.println("userPass stared");
			System.out.println(user);
			logger.debug("user input : ",user);
			logger.info("user output : ",user);
			logger.trace("program okay","output processed");
			System.out.println("userPass Ended");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		return new ResponseEntity<>(user,HttpStatus.OK); 
	}
}
