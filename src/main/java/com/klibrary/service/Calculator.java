package com.klibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
@Service
public class Calculator {
	@Autowired
	Environment Env;
	int c=0;
	int num;
	public String add(int a, int b) {
		c = a + b;
		return "ADDITION = " + c;
	}

	public String sub(int a, int b) {
		c = a - b;
		return "SUBTRACTION = " + c;
	}

	public String mul(int a, int b) {
		c = a * b;
		return "MULTIPLICATION = " + c;
	}

	public String div(int a, int b) {
		c = a / b;
		return "DIVITION = " + c;
	}

	public String prop(String operation, int a, int b) {
		
		String property = Env.getProperty(operation);
	
		if (property == null) {
			num = 4;
		} else {
			num = Integer.parseInt(property);
		}
		switch (num) {
		case 0:
			property= add(a, b);
			break;
		case 1:
			property= sub(a, b);
			break;
		case 2:
			property= mul(a, b);
			break;
		case 3:
			property= div(a, b);
			break;
		default :
			property= "Invalid input";
			break;

		}
		return property;
	}
}
