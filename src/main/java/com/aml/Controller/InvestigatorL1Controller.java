package com.aml.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aml.Entity.InvestigatorL1;
import com.aml.Service.InvestigatorL1Service;

@RestController
@RequestMapping("/l1")
public class InvestigatorL1Controller {
	
	@Autowired
	private InvestigatorL1Service L1Service;
	
	@GetMapping("/getL1details")
	public ResponseEntity<List<InvestigatorL1>> getInvstL1(){
		List<InvestigatorL1> L1list= L1Service.getL1details();
		return new ResponseEntity<List<InvestigatorL1>>(L1list,HttpStatus.OK);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<String>> getRoles(){
		return new ResponseEntity<List<String>>(L1Service.getRolls(),HttpStatus.OK);
	}
	
	@PostMapping("/sendTo/{casenumber}/{role}")
	public ResponseEntity<String> sendCasefileTo(
			@PathVariable String casenumber,
			@PathVariable String role
			){
		L1Service.sendRollTo(role,casenumber);
		
		if(role.equals("CLOSE")) {
		return ResponseEntity.ok("Case file is closed");
		}else {
		return ResponseEntity.ok("Case file sent to " + role);
		}
	}
	
	
	

}
