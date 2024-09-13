package com.myproject.ce.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myproject.ce.entity.Mess_menu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class MessDao {
	
	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public MessDao(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
		
	public List<Mess_menu> getMessMenu() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// create a query
		TypedQuery<Mess_menu> theQuery =
				currentSession.createQuery("from Mess_menu", Mess_menu.class);
		
		// execute query and get result list
		List<Mess_menu> mess = theQuery.getResultList();
		
		currentSession.close();
		return mess;
	}
}
