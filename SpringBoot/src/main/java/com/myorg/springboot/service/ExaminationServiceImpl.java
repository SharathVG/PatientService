package com.myorg.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myorg.springboot.model.Examination;
import com.myorg.springboot.model.Patient;
import com.myorg.springboot.repositories.ExaminationRepository;



@Service("ExaminationService")
@Transactional
public class ExaminationServiceImpl implements ExaminationService{

	@Autowired
	private ExaminationRepository examinationRepository;

	public Examination findById(Long id) {
		return examinationRepository.findOne(id);
	}

	public Examination findByName(String name) {
		return examinationRepository.findByName(name);
	}

	public void saveExamination(Examination exam) {
		examinationRepository.save(exam);
	}

	public void updateExamination(Examination exam){
		saveExamination(exam);
	}

	public void deleteExaminationById(Long id){
		examinationRepository.deleteExaminationById(id);
	}

	public void deleteAllExamination(){
		examinationRepository.deleteAll();
	}

	public List<Examination> findAllExaminations(){
		return examinationRepository.findAll();
	}
	
	public boolean isExaminationExist(Examination exam) {
		return findByName(exam.getName()) != null;
	}

	@Override
	public List<Examination> findByPatient(Patient patient) {
		return examinationRepository.findByPatient(patient);
	}

	@Override
	public Float getPatientBMI(Float height, Float weight) {
		return ((weight)/(height * height));
	}

}
