package com.myorg.springboot.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.myorg.springboot.model.Institution;
import com.myorg.springboot.model.Patient;
import com.myorg.springboot.service.InstitutionService;
import com.myorg.springboot.service.PatientService;
import com.myorg.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class PatientController {

	public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	PatientService patientService;
	
	@Autowired
	InstitutionService instituteService;
	
	@RequestMapping(value = "/patient/", method = RequestMethod.GET)
	public ResponseEntity<List<Patient>> listAllPatients(@PathParam("instituteId") final Long instituteId) {
		logger.info("Fetching all Patients");
		Institution instiObj = instituteService.findById(instituteId);
		List<Patient> patients = patientService.findByInstitution(instiObj);
		if (patients.isEmpty()) {
			logger.error("Patient details doesn't exist.");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPatient(@PathVariable("id") long id) {
		logger.info("Fetching Patient with id {}", id);
		Patient insti = patientService.findById(id);
		if (insti == null) {
			logger.error("Patient with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Patient with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Patient>(insti, HttpStatus.OK);
	}

	@RequestMapping(value = "/patient/age/{dob}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getPatientAge(@PathVariable("dob") long dob) {
		logger.info("Fetching Patient with id {}", dob);
		Integer age = patientService.getPatientAge(dob);
		if (age == null) {
			logger.error("Unable to calculate age.", dob);
			return new ResponseEntity(new CustomErrorType("Patient with id " + dob 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Integer>(age, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/patient/", method = RequestMethod.POST)
	public ResponseEntity<?> createPatient(@RequestBody Patient patientObj, UriComponentsBuilder ucBuilder , @PathParam("instituteId") final Long instituteId) {
		logger.info("Creating Patient : {}", patientObj);
		Institution instiObj = instituteService.findById(instituteId);
		patientObj.setInstitution(instiObj);
		if (patientService.isPatientExist(patientObj)) {
			logger.error("Unable to create. A patient with name {} already exist", patientObj.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A patient with name " + 
					patientObj.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		patientService.savePatient(patientObj);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/patient/{id}").buildAndExpand(patientObj.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
		logger.info("Updating Patient with id {}", id);

		Patient existingPatient = patientService.findById(id);

		if (existingPatient == null) {
			logger.error("Unable to update. Patient with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Patient with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		existingPatient.setName(patient.getName());
		existingPatient.setDateOfBirth(patient.getDateOfBirth());
		existingPatient.setGender(patient.getGender());

		patientService.updatePatient(existingPatient);
		return new ResponseEntity<Patient>(existingPatient, HttpStatus.OK);
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePatient(@PathVariable("id") long id) {
		logger.info(" Deleting patient with id {}", id);

		Patient patient = patientService.findById(id);
		if (patient == null) {
			logger.error("Unable to delete. Patient with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Patient with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		patientService.deletePatientById(id);
		return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/patient/", method = RequestMethod.DELETE)
	public ResponseEntity<Patient> deleteAllPatients() {
		logger.info("Deleting All Patient");

		patientService.deleteAllPatient();
		return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
	}

}