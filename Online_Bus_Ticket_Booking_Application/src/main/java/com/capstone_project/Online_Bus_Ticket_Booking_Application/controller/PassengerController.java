package com.capstone_project.Online_Bus_Ticket_Booking_Application.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.capstone_project.Online_Bus_Ticket_Booking_Application.model.Bus;
import com.capstone_project.Online_Bus_Ticket_Booking_Application.model.Passenger;
import com.capstone_project.Online_Bus_Ticket_Booking_Application.repository.BookingRepository;
import com.capstone_project.Online_Bus_Ticket_Booking_Application.repository.BusRepository;
import com.capstone_project.Online_Bus_Ticket_Booking_Application.repository.PassengerRepository;
@Controller
@RequestMapping("passenger")
public class PassengerController {

	@Autowired
	PassengerRepository passRepo;

	@Autowired
	BusRepository busRepo;

	@Autowired
	BookingRepository bookRepo;

	// **************************************************************
	// Registration controllers

	@GetMapping({ "register" })
	public String register(Model model) {
		model.addAttribute("passengerForm", new Passenger());
		return "register";
	}

	@PostMapping({ "register/save" })
	public String postRegister(@ModelAttribute Passenger passenger, Model model) {
		passRepo.save(passenger);

		// default spring security login page
		return "redirect:/login";
	}

	// **********************************************************
	// Home Controllers

	// Home page - Patient
	@GetMapping({ "welcome/{passengerId}" })
	public String welcome(@PathVariable("passengerId") int passengerId, Model model) {
		String name = passRepo.findById(passengerId).get().getPassengerName();
		model.addAttribute("id", passengerId);
		model.addAttribute("name", name);

		List<Bus> buses = busRepo.findAll();
		List<String> fromList = new ArrayList<>();
		List<String> toList = new ArrayList<>();

		for (Bus b : buses) {
			fromList.add(b.getFromLoc());
			toList.add(b.getToLoc());
		}

		model.addAttribute("from", fromList);
		model.addAttribute("to", toList);

		return "welcome";
	}

	// **********************************************************
	// Profile Update Controllers

	@GetMapping({ "modifyprofile/{passengerId}" })
	public String getPrescriptionModify(@PathVariable("passengerId") int passengerId, Model model) {
		String name = passRepo.findById(passengerId).get().getPassengerName();
		model.addAttribute("id", passengerId);
		model.addAttribute("name", name);

		Passenger passenger = passRepo.findById(passengerId).get();
		passenger.setPassengerName(passenger.getPassengerName());
		passenger.setMobile_no(passenger.getMobile_no());
		passenger.setEmail(passenger.getEmail());
		passenger.setLogin_password(passenger.getLogin_password());

		model.addAttribute("modifyPassengerForm", passenger);

		return "profile";
	}

	@PostMapping({ "modifyprofile/save" })
	public String postPrescriptionModify(@ModelAttribute("passenger") Passenger passenger) {

		passRepo.save(passenger);

		return "redirect:/passenger/welcome/" + passenger.getPassengerId();
	}

}
