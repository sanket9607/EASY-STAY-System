package com.myproject.ce.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int payment_id;
  private int registration_no;
  private String name;
  private int total_fees;
  private int fees_paid;
  
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Payment(int payment_id, int registration_no, String name, int total_fees, int fees_paid) {
		super();
		this.payment_id = payment_id;
		this.registration_no = registration_no;
		this.name = name;
		this.total_fees = total_fees;
		this.fees_paid = fees_paid;
	}
	
	public int getPayment_id() {
		return payment_id;
	}
	
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	public int getRegistration_no() {
		return registration_no;
	}
	
	public void setRegistration_no(int registration_no) {
		this.registration_no = registration_no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTotal_fees() {
		return total_fees;
	}
	
	public void setTotal_fees(int total_fees) {
		this.total_fees = total_fees;
	}
	
	public int getFees_paid() {
		return fees_paid;
	}
	
	public void setFees_paid(int fees_paid) {
		this.fees_paid = fees_paid;
	}
	
	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", registration_no=" + registration_no + ", name=" + name
				+ ", total_fees=" + total_fees + ", fees_paid=" + fees_paid + "]";
	}
}
