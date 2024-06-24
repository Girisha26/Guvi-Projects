package com.mini_project_2.Patient_Medicine_and_Appointment_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mini_project_2.Patient_Medicine_and_Appointment_System.model.Doctor;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Record: Doctor - Rest API Controllers", description = "Doctor Record API")
@RepositoryRestResource(collectionResourceRel = "doctor", path = "doctor")
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	public Doctor findByEmail(@Param("email") String email);

}
