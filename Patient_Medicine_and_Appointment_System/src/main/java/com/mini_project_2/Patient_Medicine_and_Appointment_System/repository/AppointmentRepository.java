package com.mini_project_2.Patient_Medicine_and_Appointment_System.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mini_project_2.Patient_Medicine_and_Appointment_System.model.Appointment;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Appointment History - Rest API Controllers", description = "Appointment API")
@RepositoryRestResource(collectionResourceRel = "appointment", path = "appointment")
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	public List<Appointment> findByPatientId(@Param("id") int id);

	public List<Appointment> findByDoctorId(@Param("id") int id);

	public List<Appointment> findByDoctorIdAndVisitDate(@Param("id") int id, @Param("vdate") LocalDate vdate);

}
