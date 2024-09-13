package com.myproject.ce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Rooms {

	@Id
  	private int room_no;
  	private String room_type;
  	private int no_of_students;
  	private int room_fees;
  
	
  	public Rooms() {
		super();
		// TODO Auto-generated constructor stub
	}
	
  	public Rooms(int room_no, String room_type, int no_of_students, int room_fees) {
		super();
		this.room_no = room_no;
		this.room_type = room_type;
		this.no_of_students = no_of_students;
		this.room_fees = room_fees;
	}
	
  	public int getRoom_no() {
		return room_no;
	}
	
  	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	
  	public String getRoom_type() {
		return room_type;
	}
	
  	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	
  	public int getNo_of_students() {
		return no_of_students;
	}
	
  	public void setNo_of_students(int no_of_students) {
		this.no_of_students = no_of_students;
	}
	
  	public int getRoom_fees() {
		return room_fees;
	}
	
  	public void setRoom_fees(int room_fees) {
  		this.room_fees = room_fees;
	}
	
  	@Override
	public String toString() {
		return "Rooms [room_no=" + room_no + ", room_type=" + room_type + ", no_of_students=" + no_of_students
				+ ", room_fees=" + room_fees + "]";
	}

}
