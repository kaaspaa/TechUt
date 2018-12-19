package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.shdemo.domain.Description;
import javassist.runtime.Desc;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Drug;
import com.example.shdemo.domain.Buyer;
import sun.security.krb5.internal.crypto.Des;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addBuyer(Buyer buyer) {
		buyer.setId(null);
		sessionFactory.getCurrentSession().persist(buyer);
	}
	
	@Override
	public void deleteBuyer(Buyer buyer) {
		buyer = (Buyer) sessionFactory.getCurrentSession().get(Buyer.class,
				buyer.getId());
		
		// lazy loading here
		for (Drug drug : buyer.getDrugs()) {
			drug.setAvailability(true);
			sessionFactory.getCurrentSession().update(drug);
		}
		sessionFactory.getCurrentSession().delete(buyer);
	}

	@Override
	public void deleteDrug(Drug drug) {
		drug = (Drug) sessionFactory.getCurrentSession().get(Drug.class,
				drug.getId());

		sessionFactory.getCurrentSession().delete(drug);
	}

	@Override
	public List<Drug> getOwnedDrugs(Buyer buyer) {
		buyer = (Buyer) sessionFactory.getCurrentSession().get(Buyer.class,
				buyer.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Drug> drugs = new ArrayList<Drug>(buyer.getDrugs());
		return drugs;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Buyer> getAllBuyers() {
		return sessionFactory.getCurrentSession().getNamedQuery("buyer.all")
				.list();
	}

	@Override
	public Buyer findBuyerByPin(String pin) {
		return (Buyer) sessionFactory.getCurrentSession().getNamedQuery("buyer.byPin").setString("pin", pin).uniqueResult();
	}

	@Override
	public Buyer findBuyerById(Long id){
		return (Buyer) sessionFactory.getCurrentSession().get(Buyer.class, id);
	}

	@Override
	public Long addNewDrug(Drug drug) {
		drug.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(drug);
	}

	@Override
	public List<Drug> getAvailableDrugs(){
		return sessionFactory.getCurrentSession().getNamedQuery("drug.availability").list();
	}

	@Override
	public void sellDrug(Long buyerId, Long drugId) {
		Buyer buyer = (Buyer) sessionFactory.getCurrentSession().get(
				Buyer.class, buyerId);
		Drug drug = (Drug) sessionFactory.getCurrentSession()
				.get(Drug.class, drugId);
		drug.setAvailability(false);
		buyer.getDrugs().add(drug);
	}

	@Override
	public Drug findDrugById(Long id) {
		return (Drug) sessionFactory.getCurrentSession().get(Drug.class, id);
	}

	@Override
	public void addDescription(Description description){
		description.setId(null);
		sessionFactory.getCurrentSession().save(description);
	}

	@Override
	public List<Description> getAllDescriptions() {
		return sessionFactory.getCurrentSession().getNamedQuery("description.all").list();
	}

	@Override
	public Description getDrugDescription(Drug drug){
		drug = (Drug) sessionFactory.getCurrentSession().get(Drug.class, drug.getId());

		Description description = drug.getDescription();
		return  description;
	}

	@Override
	public void deleteDescription(Description description){
		description = (Description) sessionFactory.getCurrentSession().get(Description.class, description.getId());

		sessionFactory.getCurrentSession().delete(description);
	}

}
