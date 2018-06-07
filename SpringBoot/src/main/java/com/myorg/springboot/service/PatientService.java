package com.myorg.springboot.service;


import java.util.List;

import com.myorg.springboot.model.Institution;
import com.myorg.springboot.model.Patient;

public interface PatientService {
	
	Patient findById(Long id);

	Patient findByName(String name);

	void savePatient(Patient insti);

	void updatePatient(Patient insti);

	void deletePatientById(Long id);

	void deleteAllPatient();

	List<Patient> findAllPatients();

	boolean isPatientExist(Patient insti);

	List<Patient> findByInstitution(Institution institution);

	Integer getPatientAge(long age);
}