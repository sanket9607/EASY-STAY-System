package com.myproject.ce.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.ce.entity.Student;

@Repository
//@Transactional
public class StudentDao {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public StudentDao(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public Student getStudent(final int registration_no) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		Student student = currentSession.get(Student.class, registration_no);
		currentSession.close();

		return student;
	}

	public List<Student> getStudents() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// create a query
		TypedQuery<Student> theQuery =
				currentSession.createQuery("from Student", Student.class);
		
		// execute query and get result list
		List<Student> students = theQuery.getResultList();

		currentSession.close();
		return students;
	}
	@Transactional
	public void addStudent(final Student student) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(student);
		currentSession.close();
	}

	@Transactional
	public void updateStudent(final Student student) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.merge(student);
		currentSession.close();
	}

	@Transactional
	public void deleteStudent(final int registration_no) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Student student = currentSession.get(Student.class, registration_no);
		currentSession.remove(student);
		currentSession.close();
				
	}
}
