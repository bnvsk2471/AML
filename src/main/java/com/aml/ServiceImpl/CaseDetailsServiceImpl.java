package com.aml.ServiceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.Entity.CaseDetails;
import com.aml.Repository.CaseDetailsRepo;
import com.aml.Service.CaseDetailsService;

@Service
public class CaseDetailsServiceImpl implements CaseDetailsService {
	
	@Autowired
	private CaseDetailsRepo casedetailsRepo;

	@Override
	public String saveCaseDetails(CaseDetails casedetails) {
		
		casedetails.setCaseNumber(caseNumberGenerator());
		casedetails.setCurrentStatus("Not Assigned");
		casedetailsRepo.save(casedetails);
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
