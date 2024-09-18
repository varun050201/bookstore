package com.storewithui.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storewithui.computerstore.entity.book;
@Repository
public interface bookrepository extends JpaRepository<book,Integer> {

	

}
