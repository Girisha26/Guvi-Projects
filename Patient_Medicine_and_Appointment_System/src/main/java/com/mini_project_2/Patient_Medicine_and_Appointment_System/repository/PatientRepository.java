package com.mini_project_2.Patient_Medicine_and_Appointment_System.repository;

import com.mini_project_2.Patient_Medicine_and_Appointment_System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Record: Patient - Rest API Controllers", description = "Patient Record API")
@RepositoryRestResource(collectionResourceRel = "patient", path="patient")
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	public Patient findByEmail(@Param("email") String email);
}