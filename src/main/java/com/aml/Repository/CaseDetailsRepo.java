package com.aml.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aml.Entity.CaseDetails;


@Repository
public interface CaseDetailsRepo extends JpaRepository<CaseDetails, Integer> {
	public CaseDetails findBycaseNumber(String casenumber);
}
