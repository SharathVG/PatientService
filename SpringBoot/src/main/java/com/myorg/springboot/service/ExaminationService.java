package com.myorg.springboot.service;


import java.util.List;

import com.myorg.springboot.model.Examination;
import com.myorg.springboot.model.Patient;

public interface ExaminationService {
	
	Examination findById(Long id);

	Examination findByName(String name);

	void saveExamination(Examination exam);

	void updateExamination(Examination exam);

	void deleteExaminationById(Long id);

	void deleteAllExamination();

	List<Examination> findAllExaminations();

	boolean isExaminationExist(Examination exam);

	List<Examination> findByPatient(Patient patient);

	Float getPatientBMI(Float height, Float weight);
}