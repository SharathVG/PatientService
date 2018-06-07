package com.myorg.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myorg.springboot.model.Institution;
import com.myorg.springboot.repositories.InstitutionRepository;



@Service("InstitutionService")
@Transactional
public class InstitutionServiceImpl implements InstitutionService{

	@Autowired
	private InstitutionRepository instiRepository;

	public Institution findById(Long id) {
		return instiRepository.findOne(id);
	}

	public Institution findByName(String name) {
		return instiRepository.findByName(name);
	}

	public void saveInstitution(Institution insti) {
		instiRepository.save(insti);
	}

	public void updateInstitution(Institution insti){
		saveInstitution(insti);
	}

	public void deleteInstitutionById(Long id){
		instiRepository.delete(id);
	}

	public void deleteAllInstitution(){
		instiRepository.deleteAll();
	}

	public List<Institution> findAllInstitutions(){
		return instiRepository.findAll();
	}

	public boolean isInstitutionExist(Institution insti) {
		return findByName(insti.getName()) != null;
	}

}
