package com.myproject.ce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.ce.entity.Mess_menu;
import com.myproject.ce.repository.MessDao;

@Service
public class MessService {
	
	@Autowired
	private MessDao messDao;
	
	
	public List<Mess_menu> getMessMenu() {
		return messDao.getMessMenu();
	}
	
}
