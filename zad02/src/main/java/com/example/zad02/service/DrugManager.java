package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Drug;

public interface DrugManager {
	
	public int addDrug(Drug drug);
	public List<Drug> getAllDrugs();
	
	/* batch insert - transactional */
	public void addAllDrugs(List<Drug> drugs);

}
