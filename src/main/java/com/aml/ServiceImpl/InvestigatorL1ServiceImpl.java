package com.aml.ServiceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.Entity.CaseDetails;
import com.aml.Entity.InvestigatorL1;
import com.aml.Entity.InvestigatorL2;
import com.aml.Entity.MLRO;
import com.aml.Repository.CaseDetailsRepo;
import com.aml.Repository.InvestigatorL1repo;
import com.aml.Repository.InvestigatorL2repo;
import com.aml.Repository.MLROrepo;
import com.aml.Service.InvestigatorL1Service;


@Service
public class InvestigatorL1ServiceImpl implements InvestigatorL1Service {
	
	
	@Autowired
	private InvestigatorL1repo InvestigatorL1repo;
	
	@Autowired
	private InvestigatorL2repo InvestigatorL2repo;
	
	@Autowired
	private CaseDetailsRepo casedetailsrepo;
	
	@Autowired
	MLROrepo mlrorepo;

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
			
			InvestigatorL1 details1=InvestigatorL1repo.findBycaseNumber(casenumber);
			InvestigatorL1repo.deleteById(details1.getId());
			return "CLOSED";
		}
		if(role.equals("INVESTIGATOR-L2")) {
			InvestigatorL1 details=InvestigatorL1repo.findBycaseNumber(casenumber);
			InvestigatorL2 L2entity=new InvestigatorL2();
			L2entity.setCaseNumber(details.getCaseNumber());
			InvestigatorL2repo.save(L2entity);
			
			CaseDetails status=casedetailsrepo.findBycaseNumber(casenumber);
			status.setCurrentStatus("INVESTIGATOR-L2");
			casedetailsrepo.save(status);
			
			InvestigatorL1repo.deleteById(details.getId());
			
			return "CASE MOVED TO L2";
		}
		if(role.equals("MLRO")) {
			InvestigatorL1 details=InvestigatorL1repo.findBycaseNumber(casenumber);
			MLRO MLRO=new MLRO();
			MLRO.setCaseNumber(details.getCaseNumber());
			mlrorepo.save(MLRO);
			
			CaseDetails status=casedetailsrepo.findBycaseNumber(casenumber);
			status.setCurrentStatus("MLRO");
			casedetailsrepo.save(status);
			
			InvestigatorL1 details1=InvestigatorL1repo.findBycaseNumber(casenumber);
			InvestigatorL1repo.deleteById(details1.getId());
			
			return "CASE MOVED TO MLRO";
		}
		
		
		return null;
	}

}
