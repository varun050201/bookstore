package com.storewithui.computerstore.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storewithui.computerstore.entity.book;
import com.storewithui.computerstore.repository.bookrepository;

@Service
public class bookservice {

	@Autowired	
	private bookrepository bRepo;
	
	public void save(book b) {
		bRepo.save(b);
	}
	
	public List<book> getallbook() {
		// TODO Auto-generated method stub
		return bRepo.findAll();
	}
	public book getbookbyid(int id) {
		return bRepo.findById(id).get();
	}
	public void deletebyid(int id) {
		bRepo.deleteById(id);
	}
	
}
