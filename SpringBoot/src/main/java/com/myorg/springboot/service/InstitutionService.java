package com.myorg.springboot.service;


import java.util.List;

import com.myorg.springboot.model.Institution;

public interface InstitutionService {
	
	Institution findById(Long id);

	Institution findByName(String name);

	void saveInstitution(Institution insti);

	void updateInstitution(Institution insti);

	void deleteInstitutionById(Long id);

	void deleteAllInstitution();

	List<Institution> findAllInstitutions();

	boolean isInstitutionExist(Institution insti);
}