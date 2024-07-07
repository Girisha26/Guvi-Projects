package com.capstone_project.Online_Bus_Ticket_Booking_Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.capstone_project.Online_Bus_Ticket_Booking_Application.model.Passenger;
import com.capstone_project.Online_Bus_Ticket_Booking_Application.repository.PassengerRepository;
@Controller
public class LoginController {

	@Autowired
	PassengerRepository passRepo;

	@GetMapping({ "/" })
	public String loggedIn(@AuthenticationPrincipal User user, Model model) {

		String username = user.getUsername();
		// System.out.println(username);

		String url = "";
		if (passRepo.findByEmail(username) != null) {
			Passenger p = passRepo.findByEmail(username);
			int passengerId = p.getPassengerId();
			url = "redirect:/passenger/welcome/" + passengerId;
		}

		return url;
	}

}