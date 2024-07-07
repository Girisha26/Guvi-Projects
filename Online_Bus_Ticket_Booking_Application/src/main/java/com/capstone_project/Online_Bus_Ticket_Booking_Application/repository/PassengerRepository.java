package com.capstone_project.Online_Bus_Ticket_Booking_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.capstone_project.Online_Bus_Ticket_Booking_Application.model.Passenger;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Passenger - Rest API Controllers", description = "Passenger API")
@RepositoryRestResource(collectionResourceRel = "passenger", path="passenger")
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	public Passenger findByEmail(@Param("email") String email);
}
