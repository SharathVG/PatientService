package com.myorg.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myorg.springboot.model.Institution;
import com.myorg.springboot.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByName(String name);

	List<Patient> findByInstitution(Institution institution);

	@Modifying
	void deletePatientById(@Param("id") Long  id);
	
}
