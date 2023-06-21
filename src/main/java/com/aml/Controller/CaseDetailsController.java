package com.aml.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aml.Entity.CaseDetails;
import com.aml.Service.CaseDetailsService;

@RestController
@RequestMapping("/sas")
public class CaseDetailsController {
	
	@Autowired
	private CaseDetailsService caseDetailsService;
	
	@PostMapping("/cipnum")
	public ResponseEntity<CaseDetails> getCaseDetails(
			@RequestBody CaseDetails caseDetails )
	{
		caseDetailsService.saveCaseDetails(caseDetails);
		return new ResponseEntity<CaseDetails>(caseDetails,HttpStatus.OK);
	}

}
