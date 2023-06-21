package com.aml.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SAS_CASE_DETAILS")
public class CaseDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String sasCipNumber;
	private String caseNumber;
	
	/*
	 * 1.Not Assigned
	 * 2.L1
	 * 3.L2  
	 */
	private String currentStatus;

}
