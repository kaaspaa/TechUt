package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Drug;
import com.example.shdemo.domain.Buyer;

import javax.persistence.Id;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String NAME_1 = "Damek";
	private final String PIN_1 = "789456";

	private final String NAME_2 = "Franek";
	private final String PIN_2 = "369258";

	private final String DNAME_1 = "Dekristol";
	private final String BAR_CODE_1 = "123d321c";

	private final String DNAME_2 = "Rutinoscorbit";
	private final String BAR_CODE_2 = "456g852a";

	@Test
	public void addClientCheck() {

		List<Buyer> retrievedClients = sellingManager.getAllClients();

		// If there is a client with PIN_1 delete it
		for (Buyer buyer : retrievedClients) {
			if (buyer.getPin().equals(PIN_1)) {
				sellingManager.deleteClient(buyer);
			}
		}

		Buyer buyer = new Buyer();
		buyer.setFirstName(NAME_1);
		buyer.setPin(PIN_1);

		sellingManager.addClient(buyer);

		Buyer retrievedBuyer = sellingManager.findClientByPin(PIN_1);

		assertEquals(NAME_1, retrievedBuyer.getFirstName());
		assertEquals(PIN_1, retrievedBuyer.getPin());
	}

	@Test
	public void addDrugCheck() {

		Drug drug = new Drug();
		drug.setName(DNAME_1);
		drug.setBarCode(BAR_CODE_1);
		// ... other properties here

		Long drugId = sellingManager.addNewDrug(drug);

		Drug retrievedDrug = sellingManager.findDrugById(drugId);
		assertEquals(DNAME_1, retrievedDrug.getName());
		assertEquals(BAR_CODE_1, retrievedDrug.getBarCode());
		// ... check other properties here

	}

	@Test
	public void sellDrugCheck() {

		Buyer buyer = new Buyer();
		buyer.setFirstName(NAME_2);
		buyer.setPin(PIN_2);

		sellingManager.addClient(buyer);

		Buyer retrievedBuyer = sellingManager.findClientByPin(PIN_2);

		Drug drug = new Drug();
		drug.setName(DNAME_2);
		drug.setBarCode(BAR_CODE_2);

		Long drugId = sellingManager.addNewDrug(drug);

		sellingManager.sellDrug(retrievedBuyer.getId(), drugId);

		List<Drug> ownedDrugs = sellingManager.getOwnedDrugs(retrievedBuyer);

		assertEquals(1, ownedDrugs.size());
		assertEquals(DNAME_2, ownedDrugs.get(0).getName());
		assertEquals(BAR_CODE_2, ownedDrugs.get(0).getBarCode());
	}

}
