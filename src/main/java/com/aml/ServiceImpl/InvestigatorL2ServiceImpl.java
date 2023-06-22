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
import com.aml.Service.InvestigatorL2Service;
@Service
public class InvestigatorL2ServiceImpl implements InvestigatorL2Service{
    
	@Autowired
	private InvestigatorL2repo investigatorL2repo;
	@Autowired
	private CaseDetailsRepo caseDetailsRepo;
	@Autowired
	private InvestigatorL1repo investigatorL1repo;
	@Autowired
	private MLROrepo mlrOrepo;
	
	@Override
	public List<InvestigatorL2> getL2Details() {
		return investigatorL2repo.findAll();
	}

	@Override
	public List<String> getRolls() {
		List<String> rolls= Arrays.asList("INVESTIGATOR-L1","MLRO","CLOSE");
		return rolls;
	}

	@Override
	public String sendRollTo(String role, String casenumber) {
		if(role.equals("CLOSE")) {
			CaseDetails detials = caseDetailsRepo.findBycaseNumber(casenumber);
			detials.setCurrentStatus("CLOSED");
			caseDetailsRepo.save(detials);
			InvestigatorL2 investigatorL2=investigatorL2repo.findBycaseNumber(casenumber);
			investigatorL2repo.deleteById(investigatorL2.getId());
			return "CLOSED";
		}
		if(role.equals("INVESTIGATOR-L1")) {
			InvestigatorL2 l2details =investigatorL2repo.findBycaseNumber(casenumber);
			InvestigatorL1 investigatorL1 = new InvestigatorL1();
			investigatorL1.setCaseNumber(l2details.getCaseNumber());
			investigatorL1repo.save(investigatorL1);
			investigatorL2repo.deleteById(l2details.getId());
			
			return "CASE MOVED TO L1";
		}
		if(role.equals("MLRO")) {
			InvestigatorL2 mlrodetails=investigatorL2repo.findBycaseNumber(casenumber);
			MLRO mlro = new MLRO();
			mlro.setCaseNumber(mlrodetails.getCaseNumber());
			mlrOrepo.save(mlro);
			
			investigatorL2repo.deleteById(mlrodetails.getId());
			return "CASE MOVED TO MLRO";
		}
		return null;
	}

	
}
