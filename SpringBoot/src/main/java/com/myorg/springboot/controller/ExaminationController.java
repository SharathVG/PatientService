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

import com.myorg.springboot.model.Examination;
import com.myorg.springboot.model.Institution;
import com.myorg.springboot.model.Patient;
import com.myorg.springboot.service.ExaminationService;
import com.myorg.springboot.service.PatientService;
import com.myorg.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class ExaminationController {

	public static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);

	@Autowired
	PatientService patientService;
	
	@Autowired
	ExaminationService examinationService;
	
	@RequestMapping(value = "/examination/", method = RequestMethod.GET)
	public ResponseEntity<List<Examination>> listAllExamination(@PathParam("patientId") final Long patientId) {
		logger.info("Fetching all Examinations");
		Patient patientObj = patientService.findById(patientId);
		List<Examination> examinations = examinationService.findByPatient(patientObj);
		if (examinations.isEmpty()) {
			logger.error("Examination details doesn't exist.");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Examination>>(examinations, HttpStatus.OK);
	}

	@RequestMapping(value = "/examination/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getExamination(@PathVariable("id") long id) {
		logger.info("Fetching Examination with id {}", id);
		Examination exam = examinationService.findById(id);
		if (exam == null) {
			logger.error("exam with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("exam with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Examination>(exam, HttpStatus.OK);
	}

	@RequestMapping(value = "/examination/bmi/height/{height}/weight/{weight}", method = RequestMethod.GET)
	public ResponseEntity<Float> getPatientBmi(@PathVariable("height") float height, @PathVariable("weight") float weight) {
		logger.info("Fetching Patient BMI");
		Float bmi = examinationService.getPatientBMI(height, weight);
		if (bmi == null) {
			logger.error("Unable to patient bmi.");
			return new ResponseEntity(new CustomErrorType("Unable to patient bmi."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Float>(bmi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/examination/", method = RequestMethod.POST)
	public ResponseEntity<?> createExamination(@RequestBody Examination examObj, UriComponentsBuilder ucBuilder , @PathParam("patientId") final Long patientId) {
		logger.info("Creating exam : {}", examObj);
		Patient patientObj = patientService.findById(patientId);
		examObj.setPatient(patientObj);
		if (examinationService.isExaminationExist(examObj)) {
			logger.error("Unable to create. A exam with name {} already exist", examObj.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A exam  with name " + 
					examObj.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		examinationService.saveExamination(examObj);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/examination/{id}").buildAndExpand(examObj.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/examination/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateExamination(@PathVariable("id") long id, @RequestBody Examination examObj) {
		logger.info("Updating exam with id {}", id);

		Examination existingExam= examinationService.findById(id);

		if (existingExam == null) {
			logger.error("Unable to update. exam with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. exam with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		existingExam.setName(examObj.getName());
		existingExam.setExamDate(examObj.getExamDate());
		existingExam.setHeight(examObj.getHeight());
		existingExam.setWeight(examObj.getWeight());
		existingExam.setDescription(examObj.getDescription());

		examinationService.updateExamination(existingExam);
		return new ResponseEntity<Examination>(existingExam, HttpStatus.OK);
	}

	@RequestMapping(value = "/examination/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteExamination(@PathVariable("id") long id) {
		logger.info(" Deleting exam with id {}", id);

		Examination exam = examinationService.findById(id);
		if (exam == null) {
			logger.error("Unable to delete. exam with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. exam with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		examinationService.deleteExaminationById(id);
		return new ResponseEntity<Examination>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/examination/", method = RequestMethod.DELETE)
	public ResponseEntity<Examination> deleteAllExaminations() {
		logger.info("Deleting All Examination");

		examinationService.deleteAllExamination();
		return new ResponseEntity<Examination>(HttpStatus.NO_CONTENT);
	}

}