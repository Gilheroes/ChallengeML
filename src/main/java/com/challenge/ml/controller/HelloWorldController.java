package com.challenge.ml.controller;


import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.dao.BookDAO;

@RestController
public class HelloWorldController {
	
	private static final Logger LOGGER =Logger.class.cast(HelloWorldController.class);

	@Autowired
	BookDAO bookDAO;
	
	@RequestMapping("hello")
	public String helloWorld(@RequestParam(value="name",defaultValue="World") String name) {
		return "Hello "+name+"!!";
		
	}

}
