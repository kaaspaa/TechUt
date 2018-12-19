package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.example.shdemo.domain.Description;
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

	private final String NAME_3 = "Dzbanek";
	private final String PIN_3 = "491562";

	private final String NAME_4 = "Panek";
	private final String PIN_4 = "159451";

	private final String NAME_5 = "Ulanek";
	private final String PIN_5 = "121545";

	private final String DNAME_1 = "Dekristol";
	private final String BAR_CODE_1 = "123d321c";

	private final String DNAME_2 = "Rutinoscorbit";
	private final String BAR_CODE_2 = "456g852a";

	private final String DNAME_3 = "Żubrinoscorbin";
	private final String BAR_CODE_3 = "354f123a";

	private final String DNAME_4 = "Cholinex";
	private final String BAR_CODE_4 = "354g003a";

	private final String DNAME_5 = "Meta - lek na kaszel";
	private final String BAR_CODE_5 = "931m784q";

	private final String DNAME_6 = "Chromium - lek na chromice";
	private final String BAR_CODE_6 = "912u444f";


	private final String DNAME_7 = "Bagienne ziele";
	private final String BAR_CODE_7 = "212s546u";

	private final String DNAME_8 = "Martwe meme";
	private final String BAR_CODE_8 = "784q158v";

	private final String DESCRIPTION_1 = "taki lek bierzesz se raz na jakis czas";
	private final String DESCRIPTION_2 = "psikasz do gardla 3 razy w ciagu dnia";

	private final String DNAME_9 = "Finlandija";
	private final String BAR_CODE_9 = "915d741z";
	private final String DESCRIPTION_3 = "polewasz woda i pijesz do dna 5x dziennie";

	@Test
	public void SellingManagerTest() {
		assertNotNull(sellingManager);
	}

	@Test
	public void addClientCheck() {

		List<Buyer> retrievedClients = sellingManager.getAllBuyers();

		// If there is a client with PIN_1 delete it
		for (Buyer buyer : retrievedClients) {
			if (buyer.getPin().equals(PIN_1)) {
				sellingManager.deleteBuyer(buyer);
			}
		}

		Buyer buyer = new Buyer();
		buyer.setFirstName(NAME_1);
		buyer.setPin(PIN_1);

		sellingManager.addBuyer(buyer);

		Buyer retrievedBuyer = sellingManager.findBuyerByPin(PIN_1);

		assertEquals(NAME_1, retrievedBuyer.getFirstName());
		assertEquals(PIN_1, retrievedBuyer.getPin());
	}

	@Test
	public void addDrugCheck() {

		Drug drug = new Drug();
		drug.setName(DNAME_1);
		drug.setBarCode(BAR_CODE_1);

		List<Drug> drugs = sellingManager.getAvailableDrugs();
		for (Drug drug2 : drugs) {
			if (drug2.getName().equals(DNAME_1))
				sellingManager.deleteDrug(drug2);
		}

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

		sellingManager.addBuyer(buyer);

		Buyer retrievedBuyer = sellingManager.findBuyerByPin(PIN_2);

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

	@Test
	public void findBuyerByIDCheck() {
		Buyer buyer = new Buyer();

		buyer.setFirstName(NAME_3);
		buyer.setPin(PIN_3);

		Drug drug1 = new Drug();
		Drug drug2 = new Drug();
		Drug drug3 = new Drug();


		drug1.setName(DNAME_3);
		drug2.setName(DNAME_4);
		drug3.setName(DNAME_5);
		drug1.setBarCode(BAR_CODE_3);
		drug2.setBarCode(BAR_CODE_4);
		drug3.setBarCode(BAR_CODE_5);

		sellingManager.addBuyer(buyer);
		sellingManager.addNewDrug(drug1);
		sellingManager.addNewDrug(drug2);
		sellingManager.addNewDrug(drug3);

		Buyer testBuyer = new Buyer();
		testBuyer = sellingManager.findBuyerById(buyer.getId());

		assertEquals(NAME_3,testBuyer.getFirstName());
		assertEquals(PIN_3,testBuyer.getPin());
	}

		@Test
	public void findBuyerByPinCheck() {
		Buyer buyer = new Buyer();

		buyer.setFirstName(NAME_3);
		buyer.setPin(PIN_3);

		Drug drug1 = new Drug();
		Drug drug2 = new Drug();
		Drug drug3 = new Drug();


		drug1.setName(DNAME_3);
		drug2.setName(DNAME_4);
		drug3.setName(DNAME_5);
		drug1.setBarCode(BAR_CODE_3);
		drug2.setBarCode(BAR_CODE_4);
		drug3.setBarCode(BAR_CODE_5);

		sellingManager.addBuyer(buyer);
		sellingManager.addNewDrug(drug1);
		sellingManager.addNewDrug(drug2);
		sellingManager.addNewDrug(drug3);

		Buyer testBuyer = new Buyer();
		testBuyer = sellingManager.findBuyerByPin(buyer.getPin());

		assertEquals(NAME_3,testBuyer.getFirstName());
		assertEquals(PIN_3,testBuyer.getPin());
	}
	
	@Test
	public void getDrugByIDCheck() {
		Drug d = new Drug();
		d.setName(DNAME_6);
		d.setBarCode(BAR_CODE_6);
		d.setAvailability(true);

		List<Drug> drugs = sellingManager.getAvailableDrugs();
		for (Drug drug: drugs){
			if(drug.getName().equals(DNAME_6))
				sellingManager.deleteDrug(drug);
		}

		sellingManager.addNewDrug(d);

		Drug d2 = new Drug();
		d2 = sellingManager.findDrugById(d.getId());

		assertEquals(d.getId(), d2.getId());
		assertEquals(d.getBarCode(),d2.getBarCode());
	}

	@Test
	public void getAllBuyersTest() {
		Buyer b = new Buyer();
		b.setFirstName(NAME_4);
		b.setPin(PIN_4);
		Buyer b2 = new Buyer();
		b2.setFirstName(NAME_5);
		b2.setPin(PIN_5);

		for (Buyer buyers: sellingManager.getAllBuyers()) {
			if (buyers.getFirstName().equals(NAME_4) || buyers.getFirstName().equals(NAME_5))
				sellingManager.deleteBuyer(buyers);
		}

		sellingManager.addBuyer(b);
		sellingManager.addBuyer(b2);

		List<Buyer> buyers = sellingManager.getAllBuyers();

		assertEquals(buyers.size(),2);
	}

	@Test
	public void getAllDescriptionTest() {
		Description ds = new Description();
		ds.setDescription(DESCRIPTION_1);
		Description ds2 = new Description();
		ds2.setDescription(DESCRIPTION_2);

		sellingManager.addDescription(ds);
		sellingManager.addDescription(ds2);

		List<Description> test = sellingManager.getAllDescriptions();

		assertEquals(test.size(),2);
	}

	@Test
	public void addDesctiptionToDrugTest() {
		Drug d = new Drug();
		d.setName(DNAME_9);
		d.setBarCode(BAR_CODE_9);

		Description ds = new Description();
		ds.setDescription(DESCRIPTION_3);

		sellingManager.addNewDrug(d);
		sellingManager.addDescription(ds);

		d.setDescription(ds);

		Description test = sellingManager.getDrugDescription(d);

		assertEquals(test.getDescription(),d.getDescription());
	}



}
