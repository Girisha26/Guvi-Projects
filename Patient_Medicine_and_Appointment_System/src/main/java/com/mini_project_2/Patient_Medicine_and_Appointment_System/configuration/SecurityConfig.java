package com.mini_project_2.Patient_Medicine_and_Appointment_System.configuration;

import com.mini_project_2.Patient_Medicine_and_Appointment_System.model.Doctor;
import com.mini_project_2.Patient_Medicine_and_Appointment_System.model.Patient;
import com.mini_project_2.Patient_Medicine_and_Appointment_System.repository.DoctorRepository;
import com.mini_project_2.Patient_Medicine_and_Appointment_System.repository.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// Adding Doctor and Patient Credentials
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	
	// 1. Authentication
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {

		// For Testing Purpose
		
		// 1 Admin
		UserDetails admin = User.withUsername("admin")
				.password(encoder.encode("admin@"))
				.roles("ADMIN")
				.build();
		
		// 2. User
		UserDetails user = User.withUsername("user")
				.password(encoder.encode("user@"))
				.roles("USER")
				.build();
		
		
		// Actual User Credentials
		// Adding Doctor & Login Credentials
		List<Doctor> docs = doctorRepository.findAll();
		List<Patient> pats = patientRepository.findAll();
		
		List<UserDetails> doctors = new ArrayList<>();
		List<UserDetails> patients = new ArrayList<>();
		
		// 3. Doctors	
		for(Doctor doc: docs) {
			doctors.add(User.withUsername(doc.getEmail())
				.password(encoder.encode(doc.getLogin_password()))
				.roles("USER")
				.build()
			);
		}
		
		
		// 4. Patients
		
		for(Patient pat: pats)
		{
			patients.add(User.withUsername(pat.getEmail())
							.password(encoder.encode(pat.getLogin_password()))
							.roles("USER")
							.build()
						);
		}

		Collection<UserDetails> allUsers = new ArrayList<>();
		allUsers.addAll(doctors);
		allUsers.addAll(patients);
		return new InMemoryUserDetailsManager(allUsers);
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	
	// 2. Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(AbstractHttpConfigurer::disable)
	    		.formLogin(withDefaults())
	    		.build();		

	}

}