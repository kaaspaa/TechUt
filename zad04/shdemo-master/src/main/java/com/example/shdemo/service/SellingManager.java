package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Drug;
import com.example.shdemo.domain.Buyer;

public interface SellingManager {
	
	void addClient(Buyer buyer);
	List<Buyer> getAllClients();
	void deleteClient(Buyer buyer);
	Buyer findClientByPin(String pin);
	Buyer findBuyerById(Long id);

	Long addNewDrug(Drug drug);
	List<Drug> getAvailableDrugs();
	Drug findDrugById(Long id);

	List<Drug> getOwnedDrugs(Buyer buyer);
	void sellDrug(Long buyerId, Long drugId);

}
