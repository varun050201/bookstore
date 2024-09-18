package com.storewithui.computerstore.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storewithui.computerstore.entity.mybooklist;
import com.storewithui.computerstore.repository.mybookrepository;

@Service
public class mybooklistservice {
	@Autowired	
	private mybookrepository mybook;

public void savemybook(mybooklist book) {
	mybook.save(book );
}

public List<mybooklist> getAllMyBooks(){
	return mybook.findAll();
}

public void deletebyid(int id) {
	mybook.deleteById(id);
}





}


