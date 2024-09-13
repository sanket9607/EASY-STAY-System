package com.myproject.ce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.ce.entity.Mess_menu;
import com.myproject.ce.entity.Payment;
import com.myproject.ce.entity.Rooms;
import com.myproject.ce.entity.Student;
import com.myproject.ce.helper.Message;
import com.myproject.ce.service.MessService;
import com.myproject.ce.service.PaymentService;
import com.myproject.ce.service.RoomService;
import com.myproject.ce.service.StudentService;

import jakarta.servlet.http.HttpSession;


@Controller
public class AdminController {
	
	@Autowired 
	public RoomService roomService;
	
	@Autowired
	public StudentService studentService;
	
	@Autowired
	public PaymentService paymentService;
	
	@Autowired
	public MessService messService;
	
	@RequestMapping("/test")
	public String firstHandler() {
		return "test";
	}
	
	@RequestMapping("/dashboard")
	public String dashboardHandler(ModelMap studentModel) {
		List <Student> students = studentService.getStudents();
		int len = students.size();
		List<Rooms> room = roomService.getRooms();
		int tr = room.size();
		
		Set<Integer> rm = new TreeSet<Integer>();
		students.forEach((i)->{rm.add(i.getRoom_no());});
		int br = rm.size();
		
		studentModel.addAttribute("students", students);
		studentModel.addAttribute("length", len);
		studentModel.addAttribute("total_room", tr);
		studentModel.addAttribute("booked_room", br);
		return "dashboard";
	}
	
	@RequestMapping("/reg_student")
	public String regStudentHandler() {
		
		return "reg_student";
	}
	
	@PostMapping("/student_registered")
	public String studentRegistered(@ModelAttribute("student") Student student, ModelMap studentModel, RedirectAttributes redirAttrs){
//		System.out.println(student);
		List <Student> students = studentService.getStudents();
		
		List<Integer> ts = new ArrayList<Integer>();
		students.forEach((i)->{if(student.getRoom_no() == i.getRoom_no())ts.add(i.getRoom_no());});
		Rooms room = roomService.getRoom(student.getRoom_no());
		
		if(ts.size() < room.getNo_of_students()) {
			studentService.addStudent(student);
			studentModel.addAttribute("students", students);
			redirAttrs.addFlashAttribute("msg", new Message("Student successfully Registered.", "alert-success alert-dismissible fade show"));
		}
		else {
			redirAttrs.addFlashAttribute("msg", new Message("Room is full. Please select another room.", "alert-danger alert-dismissible fade show"));
		}
		return "redirect:/reg_student";
	}
	
	@RequestMapping("/login_student")
	public String loginStudentHandler() {
		return "student_login";
	}
	
	@RequestMapping("/payment")
	public String paymentHandler(@RequestParam(name = "regno") int regno, @RequestParam(name = "email") String email, ModelMap studentModel, ModelMap roomModel, ModelMap paymentModel, RedirectAttributes redirAttrs) {
		if(studentService.getStudent(regno) != null) {
			Student student = studentService.getStudent(regno);
			if(student.getEmail().equals(email)) {
				Rooms room = roomService.getRoom(student.getRoom_no());
				Payment payments = paymentService.getPaymentsByRegno(student.getRegistration_no());
				
				studentModel.addAttribute("student", student);
				roomModel.addAttribute("room", room);
				paymentModel.addAttribute("payment", payments);
				
				if(payments.getPayment_id() == 0) {
					Payment payment = new Payment();
					payment.setName(student.getFirst_name());
					payment.setFees_paid(0);
					payment.setRegistration_no(regno);
					payment.setTotal_fees(room.getRoom_fees());
					paymentService.addPayment(payment);
					payment = paymentService.getPaymentsByRegno(student.getRegistration_no());
					paymentModel.addAttribute("payment", payment);
					return "payment";
				}
			}
			else {
				redirAttrs.addFlashAttribute("msg", new Message("Error - Invalid E-mail Credential !", "alert-danger alert-dismissible fade show"));
				return "redirect:/login_student";
			}
		}
		else {
			redirAttrs.addFlashAttribute("msg", new Message("Error - Invalid registration number !", "alert-danger alert-dismissible fade show"));
			return "redirect:/login_student";
		}
		
		paymentModel.addAttribute("activef", "selected");
//		System.out.println(payments);
		return "payment";
	}
	
	@PostMapping("/payment_success")
	public String payment_success(@ModelAttribute("payment") Payment payment, @RequestParam(name = "amt") int amt, ModelMap paymentModel, RedirectAttributes redirAttrs){
		int fee = payment.getFees_paid() + amt;
		if(fee > payment.getTotal_fees()) {
			redirAttrs.addFlashAttribute("msg", new Message("Error - Amount should not be greater than total fees.", "alert-danger alert-dismissible fade show"));
			return "redirect:/login_student";
		}
		else {
			payment.setFees_paid(fee);
//			System.out.println(payment);
			paymentService.updatePayment(payment);
		
			List <Payment> payments = paymentService.getPayments();
			paymentModel.addAttribute("payments", payments);
			redirAttrs.addFlashAttribute("msg", new Message("Payment details updated successfully.", "alert-success alert-dismissible fade show"));
			return "redirect:/login_student";
		}
	}
	
	@RequestMapping("/view_student")
	public String viewStudentHandler(ModelMap studentModel) {
		List <Student> students = studentService.getStudents();
		studentModel.addAttribute("students", students);
		return "view_student";
	}
	
	@RequestMapping("/manage_rooms")
	public String roomsHandler(ModelMap roomModel) {
		List <Rooms> rooms = roomService.getRooms();
		roomModel.addAttribute("rooms", rooms);
		return "manage_rooms";
	}
	
	@RequestMapping("/mess_menu")
	public String messMenuHandler(ModelMap messModel) {
		List <Mess_menu> mess = messService.getMessMenu();
		messModel.addAttribute("mess", mess);
		System.out.println(mess);
		return "mess_menu";
	}
	
	@RequestMapping("/add_room")
	public String addRoomHandler(ModelMap r) {
		r.addAttribute("activer", "selected");
		return "add_room";
	}
	
	@PostMapping("/room_added")
	public String roomAddedHandler(@ModelAttribute("room") Rooms room, ModelMap roomModel, RedirectAttributes redirAttrs) {
//		System.out.println(room);
		roomService.addRooms(room);
		List <Rooms> rooms = roomService.getRooms();
		roomModel.addAttribute("rooms", rooms);
		redirAttrs.addFlashAttribute("msg", new Message("Room details added successfully.", "alert-success alert-dismissible fade show"));
		return "redirect:/manage_rooms";
	}
	
	
	@RequestMapping("/delete_room")
	public String roomDeleteHandler(@RequestParam (name = "r_id") int r_id, ModelMap rdModel, RedirectAttributes redirAttrs) {
		
		List <Student> students = studentService.getStudents();
		List<Integer> ts = new ArrayList<Integer>();
		students.forEach((i)->{if(r_id == i.getRoom_no())ts.add(i.getRoom_no());});
		
		System.out.println(ts.size());
		
		if(ts.size() > 0) {
			redirAttrs.addFlashAttribute("msg", new Message("Error - Room is not Empty.", "alert-danger alert-dismissible fade show"));
		}
		else {
			roomService.deleteRooms(r_id);
//			System.out.println(r_id);
			redirAttrs.addFlashAttribute("msg", new Message("Room details deleted successfully.", "alert-success alert-dismissible fade show"));
		}
		
		return "redirect:/manage_rooms";
	}
	
	
	@RequestMapping("/delete_student")
	public String studentDeleteHandler(@RequestParam (name = "s_id") int s_id, ModelMap sModel, RedirectAttributes redirAttrs) {
		studentService.deleteStudent(s_id);
		
		Payment payment = paymentService.getPaymentsByRegno(s_id);
		paymentService.deletePayment(payment.getPayment_id());
		
		redirAttrs.addFlashAttribute("msg", new Message("Student details deleted successfully.", "alert-success alert-dismissible fade show"));
		return "redirect:/view_student";
	}
}
