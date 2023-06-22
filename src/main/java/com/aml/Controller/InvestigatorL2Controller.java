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

import com.aml.Entity.InvestigatorL2;
import com.aml.Service.InvestigatorL2Service;

@RestController
@RequestMapping("/l2")
public class InvestigatorL2Controller {
	@Autowired
    private InvestigatorL2Service investigatorL2Service;
	
	@GetMapping("/getL2details")
	public ResponseEntity<List<InvestigatorL2>> getL2Data(){
		List<InvestigatorL2> l2list = investigatorL2Service.getL2Details();
		return new ResponseEntity<List<InvestigatorL2>>(l2list,HttpStatus.OK);
	}
	@GetMapping("/rolls")
	public ResponseEntity<List<String>> getRolls(){
		return new ResponseEntity<List<String>>(investigatorL2Service.getRolls(),HttpStatus.OK);
	}
	@PostMapping("/sendTo/{casenumber}/{role}")
	public ResponseEntity<String> sendCasefileTo(
			@PathVariable String casenumber,
			@PathVariable String role){
		return ResponseEntity.ok(investigatorL2Service.sendRollTo(role, casenumber));
	}

}
