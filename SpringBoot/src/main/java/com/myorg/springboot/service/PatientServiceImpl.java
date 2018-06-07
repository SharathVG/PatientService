package com.myorg.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myorg.springboot.model.Institution;
import com.myorg.springboot.model.Patient;
import com.myorg.springboot.repositories.PatientRepository;



@Service("PatientService")
@Transactional
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;

	public Patient findById(Long id) {
		return patientRepository.findOne(id);
	}

	public Patient findByName(String name) {
		return patientRepository.findByName(name);
	}

	public void savePatient(Patient patient) {
		patientRepository.save(patient);
	}

	public void updatePatient(Patient patient){
		savePatient(patient);
	}

	public void deletePatientById(Long id){
		patientRepository.deletePatientById(id);
	}

	public void deleteAllPatient(){
		patientRepository.deleteAll();
	}

	public List<Patient> findAllPatients(){
		return patientRepository.findAll();
	}
	
	public boolean isPatientExist(Patient patient) {
		return findByName(patient.getName()) != null;
	}

	@Override
	public List<Patient> findByInstitution(Institution institution) {
		return patientRepository.findByInstitution(institution);
	}

	@Override
	public Integer getPatientAge(long birthDate) {
		Date now = new Date();
		long timeBetween = now.getTime() - birthDate;
		double yearsBetween = timeBetween / 3.15576e+10;
		return (int) Math.floor(yearsBetween);
	}

}
