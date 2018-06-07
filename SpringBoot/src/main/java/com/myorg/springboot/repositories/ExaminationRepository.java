package com.myorg.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myorg.springboot.model.Examination;
import com.myorg.springboot.model.Patient;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

	Examination findByName(String name);

	List<Examination> findByPatient(Patient patient);

	@Modifying
	void deleteExaminationById(@Param("id") Long  id);
	
}
