package com.aml.Service;

import java.util.List;

import com.aml.Entity.InvestigatorL1;


public interface InvestigatorL1Service {
	
	public List<InvestigatorL1> getL1details();
	public List<String> getRolls();
	
	public String sendRollTo(String role, String casenumber);

}
