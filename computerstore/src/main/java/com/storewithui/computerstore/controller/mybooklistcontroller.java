package com.storewithui.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storewithui.computerstore.service.mybooklistservice;



@Controller
public class mybooklistcontroller {
	
	@Autowired
	private mybooklistservice service;
	
	@RequestMapping("/deleteMyList/{id}")
	 String deletemylist(@PathVariable("id") int id) {
		service.deletebyid(id);
		return "redirect:/my_books";
	}
}


