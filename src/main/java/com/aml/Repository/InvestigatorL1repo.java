package com.aml.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aml.Entity.InvestigatorL1;

@Repository
public interface InvestigatorL1repo extends JpaRepository<InvestigatorL1, Integer>{
	
	

}
