package com.myproject.ce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mess_menu {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String m_id;
	private String Day;
	private String Breakfast;
	private String Lunch;
	private String Dinner;
	
	public Mess_menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Mess_menu(String day, String breakFast, String lunch, String dinner) {
		super();
		Day = day;
		Breakfast = breakFast;
		Lunch = lunch;
		Dinner = dinner;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getBreakFast() {
		return Breakfast;
	}

	public void setBreakFast(String breakFast) {
		Breakfast = breakFast;
	}

	public String getLunch() {
		return Lunch;
	}

	public void setLunch(String lunch) {
		Lunch = lunch;
	}

	public String getDinner() {
		return Dinner;
	}

	public void setDinner(String dinner) {
		Dinner = dinner;
	}

	@Override
	public String toString() {
		return "Mess_menu [m_id=" + m_id + ", Day=" + Day + ", BreakFast=" + Breakfast + ", Lunch=" + Lunch
				+ ", Dinner=" + Dinner + "]";
	}
}
