package com.myorg.springboot.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","Institution");
		return "index";
	}
	

	@RequestMapping("/Patient")
	String Patient(ModelMap modal, @PathParam("instituteName") final String instituteName, @PathParam("instituteId") final String instituteId) {
		modal.addAttribute("title","Patient");
		modal.addAttribute("InstituteName",instituteName);
		modal.addAttribute("InstituteId",instituteId);
		return "patient";
	}

	@RequestMapping("/Examination")
	String Examination(ModelMap modal, @PathParam("patientName") final String patientName, @PathParam("patientId") final String patientId, @PathParam("instituteName") final String instituteName, @PathParam("instituteId") final String instituteId) {
		modal.addAttribute("title","Examination");
		modal.addAttribute("PatientName",patientName);
		modal.addAttribute("PatientId",patientId);
		modal.addAttribute("InstituteName",instituteName);
		modal.addAttribute("InstituteId",instituteId);
		return "examination";
	}
	
	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}

}
