package com.myproject.ce.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.ce.entity.Rooms;

@Repository
//@Transactional
public class RoomsDao {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public RoomsDao(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public Rooms getRoom(final int room_no) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		Rooms room = currentSession.get(Rooms.class, room_no);
		currentSession.close();
		return room;
	}

	public List<Rooms> getRooms() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// create a query
		TypedQuery<Rooms> theQuery =
				currentSession.createQuery("from Rooms", Rooms.class);
		
		// execute query and get result list
		List<Rooms> rooms = theQuery.getResultList();

		currentSession.close();
		return rooms;
	}
	
	@Transactional
	public void addRooms(final Rooms room) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(room);
		currentSession.close();
	}

	@Transactional
	public void updateRooms(final Rooms room) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.merge(room);
		currentSession.close();
	}

	@Transactional
	public void deleteRooms(final int room_no) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Rooms room = currentSession.get(Rooms.class, room_no);
		currentSession.remove(room);
		currentSession.close();
				
	}

}
