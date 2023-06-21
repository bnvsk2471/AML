package com.aml.ServiceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.Entity.CaseDetails;
import com.aml.Entity.InvestigatorL1;
import com.aml.Repository.CaseDetailsRepo;
import com.aml.Repository.InvestigatorL1repo;
import com.aml.Service.CaseDetailsService;

@Service
public class CaseDetailsServiceImpl implements CaseDetailsService {
	
	@Autowired
	private CaseDetailsRepo casedetailsRepo;
	
	@Autowired
	private InvestigatorL1repo L1Repo;

	@Override
	public String saveCaseDetails(CaseDetails casedetails) {
		
		
		casedetails.setCaseNumber(caseNumberGenerator());
		casedetails.setCurrentStatus("Assigned to L1");
		casedetailsRepo.save(casedetails);
		
		
		InvestigatorL1 L1Entity=new InvestigatorL1();
		L1Entity.setCaseNumber(casedetails.getCaseNumber());
		L1Repo.save(L1Entity);
		
		return "DETAILS SAVED";
	}
	
	public String caseNumberGenerator() {
		String text="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder builder=new StringBuilder();
		Random random=new Random();
		int pwdLength=10;
		for(int i=1;i<=pwdLength;i++) {
			int index=random.nextInt(text.length());
			builder.append(text.charAt(index));
		}
		return builder.toString();
	}

}
