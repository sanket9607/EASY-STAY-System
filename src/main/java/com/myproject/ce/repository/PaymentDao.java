package com.myproject.ce.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.ce.entity.Payment;

@Repository
//@Transactional
public class PaymentDao {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public PaymentDao(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public Payment getPayment(final int payment_id) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		Payment payment = currentSession.get(Payment.class, payment_id);
		currentSession.close();
		return payment;
	}

	public List<Payment> getPayments() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// create a query
		TypedQuery<Payment> theQuery =
				currentSession.createQuery("from Payment", Payment.class);
		
		// execute query and get result list
		List<Payment> payments = theQuery.getResultList();

		currentSession.close();
		return payments;
	}
	
	@Transactional
	public void addPayment(final Payment payment) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(payment);
		currentSession.close();
	}

	@Transactional
	public void updatePayment(final Payment payment) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.merge(payment);
		
		currentSession.close();
	}

	@Transactional
	public void deletePayment(final int regno) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Payment payment = currentSession.get(Payment.class, regno);
		
		if(payment != null) {
			currentSession.remove(payment);
		}
		currentSession.close();
	}
	
	
	public Payment getPaymentsByRegno(final int regno) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		try {
		// create a query
		TypedQuery<Payment> theQuery =
				currentSession.createQuery("SELECT p FROM Payment p WHERE p.registration_no=:regno", Payment.class);
		theQuery.setParameter("regno", regno);

		currentSession.close();
		
		
			return theQuery.getSingleResult();
		}
		catch(Exception E) {
			Payment payment = new Payment();
			return payment;
		}
		
	}

}
