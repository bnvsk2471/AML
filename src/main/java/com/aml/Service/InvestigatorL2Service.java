package com.aml.Service;

import java.util.List;

import com.aml.Entity.InvestigatorL2;

public interface InvestigatorL2Service {

	public List<InvestigatorL2> getL2Details();
	public List<String> getRolls();
	public String sendRollTo(String role, String casenumber);
}
