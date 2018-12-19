package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Description;
import com.example.shdemo.domain.Drug;
import com.example.shdemo.domain.Buyer;

public interface SellingManager {
	
	void addBuyer(Buyer buyer);
	List<Buyer> getAllBuyers();
	void deleteBuyer(Buyer buyer);
	void deleteDrug(Drug drug);
	Buyer findBuyerByPin(String pin);
	Buyer findBuyerById(Long id);
	
	Long addNewDrug(Drug drug);
	List<Drug> getAvailableDrugs();
	Drug findDrugById(Long id);

	List<Drug> getOwnedDrugs(Buyer buyer);
	void sellDrug(Long buyerId, Long drugId);

	void addDescription(Description description);
	List<Description> getAllDescriptions();
	Description getDrugDescription(Drug drug);
	void deleteDescription(Description description);

}