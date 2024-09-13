package com.myproject.ce.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.ce.entity.Student;
import com.myproject.ce.repository.StudentDao;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	public Student getStudent(final int registration_no) {
		return studentDao.getStudent(registration_no);
	}

	public List<Student> getStudents() {
		return studentDao.getStudents();
	}
	
	public void addStudent(final Student student) {
		studentDao.addStudent(student);
	}

	public void updateStudent(final Student student) {
		studentDao.updateStudent(student);
	}

	public void deleteStudent(final int registration_no) {
		studentDao.deleteStudent(registration_no);	
	}
}
