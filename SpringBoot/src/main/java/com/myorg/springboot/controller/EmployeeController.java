package com.myorg.springboot.controller;

import java.util.List;

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
import com.myorg.springboot.service.InstitutionService;
import com.myorg.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	InstitutionService instiService;

	@RequestMapping(value = "/institution/", method = RequestMethod.GET)
	public ResponseEntity<List<Institution>> listAllInstitutions() {
		logger.info("Fetching all Institutions");
		List<Institution> institutions = instiService.findAllInstitutions();
		if (institutions.isEmpty()) {
			logger.error("Institution details doesn't exist.");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Institution>>(institutions, HttpStatus.OK);
	}

	@RequestMapping(value = "/institution/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getInstitution(@PathVariable("id") long id) {
		logger.info("Fetching Institution with id {}", id);
		Institution insti = instiService.findById(id);
		if (insti == null) {
			logger.error("Institution with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Institution with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Institution>(insti, HttpStatus.OK);
	}

	@RequestMapping(value = "/institution/", method = RequestMethod.POST)
	public ResponseEntity<?> createInstitution(@RequestBody Institution insti, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Institution : {}", insti);

		if (instiService.isInstitutionExist(insti)) {
			logger.error("Unable to create. A institution with name {} already exist", insti.getName());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A institution with name " + insti.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		instiService.saveInstitution(insti);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/institution/{id}").buildAndExpand(insti.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/institution/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateInstitution(@PathVariable("id") long id, @RequestBody Institution insti) {
		logger.info("Updating Institution with id {}", id);

		Institution existingInsti = instiService.findById(id);

		if (existingInsti == null) {
			logger.error("Unable to update. Institution with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Institution with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		existingInsti.setName(insti.getName());
		existingInsti.setDescription(insti.getDescription());

		instiService.updateInstitution(existingInsti);
		return new ResponseEntity<Institution>(existingInsti, HttpStatus.OK);
	}

	@RequestMapping(value = "/institution/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteInstitution(@PathVariable("id") long id) {
		logger.info(" Deleting institution with id {}", id);

		Institution insti = instiService.findById(id);
		if (insti == null) {
			logger.error("Unable to delete. Institution with id {} not found.", id);
			return new ResponseEntity(
					new CustomErrorType("Unable to delete. Institution with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		instiService.deleteInstitutionById(id);
		return new ResponseEntity<Institution>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/institution/", method = RequestMethod.DELETE)
	public ResponseEntity<Institution> deleteAllInstitutions() {
		logger.info("Deleting All Institution");

		instiService.deleteAllInstitution();
		return new ResponseEntity<Institution>(HttpStatus.NO_CONTENT);
	}

}