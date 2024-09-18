package com.storewithui.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storewithui.computerstore.entity.mybooklist;

@Repository
public interface mybookrepository  extends JpaRepository<mybooklist,Integer>{

}
