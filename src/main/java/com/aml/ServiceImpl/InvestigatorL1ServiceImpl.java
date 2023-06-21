package com.aml.ServiceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.Entity.CaseDetails;
import com.aml.Entity.InvestigatorL1;
import com.aml.Repository.CaseDetailsRepo;
import com.aml.Repository.InvestigatorL1repo;
import com.aml.Service.InvestigatorL1Service;


@Service
public class InvestigatorL1ServiceImpl implements InvestigatorL1Service {
	
	
	@Autowired
	private InvestigatorL1repo InvestigatorL1repo;
	
	@Autowired
	private CaseDetailsRepo casedetailsrepo;

	@Override
	public List<InvestigatorL1> getL1details() {
		return InvestigatorL1repo.findAll();
	}

	@Override
	public List<String> getRolls() {
		List<String> rolls=Arrays.asList("INVESTIGATOR-L2","MLRO","CLOSE");
		return rolls;
	}

	@Override
	public String sendRollTo(String role,String casenumber) {
		if(role.equals("CLOSE")) {
			CaseDetails details=casedetailsrepo.findBycaseNumber(casenumber);
			details.setCurrentStatus("CLOSED");
			casedetailsrepo.save(details);
		}
		return "CLOSED";
	}

}
