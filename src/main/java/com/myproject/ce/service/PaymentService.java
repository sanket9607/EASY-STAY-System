package com.myproject.ce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.ce.entity.Payment;
import com.myproject.ce.repository.PaymentDao;


@Service
public class PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;
	
	public Payment getPayment(final int payment_id) {
		return paymentDao.getPayment(payment_id);
	}

	public List<Payment> getPayments() {
		return paymentDao.getPayments();
	}

	public void addPayment(final Payment payment) {
		paymentDao.addPayment(payment);
	}

	public void updatePayment(final Payment payment) {
		paymentDao.updatePayment(payment);
	}

	public void deletePayment(final int regno) {
		paymentDao.deletePayment(regno);	
	}
	
	public Payment getPaymentsByRegno(final int regno) {
		return paymentDao.getPaymentsByRegno(regno);
	}
}
