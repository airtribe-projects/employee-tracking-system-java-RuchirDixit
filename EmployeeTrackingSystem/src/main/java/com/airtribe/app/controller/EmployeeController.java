package com.airtribe.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@GetMapping("/")
	public String demo() {
		return "hello, from demo!";
	}
	
	@GetMapping("/hey")
	public String demo1() {
		return "hello, from demo!";
	}

}
