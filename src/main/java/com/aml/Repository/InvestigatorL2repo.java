package com.aml.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aml.Entity.CaseDetails;
import com.aml.Entity.InvestigatorL2;

@Repository
public interface InvestigatorL2repo extends JpaRepository<InvestigatorL2, Integer>{
	public CaseDetails findBycaseNumber(String casenumber);
}
