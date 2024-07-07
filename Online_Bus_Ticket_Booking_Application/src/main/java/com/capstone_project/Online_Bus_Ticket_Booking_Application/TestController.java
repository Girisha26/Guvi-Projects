package com.capstone_project.Online_Bus_Ticket_Booking_Application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("test")
	public String testingPage(Model model) {
		model.addAttribute("name", "Giri");
		return "test";
	}

}
