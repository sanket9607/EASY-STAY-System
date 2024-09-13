package com.myproject.ce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

  @Id
//  @GeneratedValue(strategy=GenerationType.AUTO)
  private int registration_no;
  private int room_no;
  private String starting_date;
  private String first_name;
  private String last_name;
  private String email;
  private String gender;
  private long contact_no;
  private String address;
  private String city;
  private int pincode;

  public Student() {
	super();
	// TODO Auto-generated constructor stub
  }

  public Student(int registration_no, int room_no, String starting_date, String first_name, String last_name,
		String email, String gender, long contact_no, String address, String city, int pincode) {
		super();
		this.registration_no = registration_no;
		this.room_no = room_no;
		this.starting_date = starting_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.gender = gender;
		this.contact_no = contact_no;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
  }

	public int getRegistration_no() {
		return registration_no;
	}

	public void setRegistration_no(int registration_no) {
		this.registration_no = registration_no;
	}

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getStarting_date() {
		return starting_date;
	}

	public void setStarting_date(String starting_date) {
		this.starting_date = starting_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getContact_no() {
		return contact_no;
	}

	public void setContact_no(long contact_no) {
		this.contact_no = contact_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Student [registration_no=" + registration_no + ", room_no=" + room_no + ", starting_date="
				+ starting_date + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", gender=" + gender + ", contact_no=" + contact_no + ", address=" + address + ", city=" + city
				+ ", pincode=" + pincode + "]";
	}

}
