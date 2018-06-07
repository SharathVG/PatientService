package com.myorg.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myorg.springboot.model.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

	Institution findByName(String name);

}
