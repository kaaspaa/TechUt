package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "buyer.all", query = "Select b from Buyer b"),
	@NamedQuery(name = "buyer.byPin", query = "Select b from Buyer b where b.pin = :pin")
})
public class Buyer {

	private Long id;

	private String firstName = "unknown";
	private String pin = "";
	private Date registrationDate = new Date();

	private List<Drug> drugs = new ArrayList<Drug>();
	private List<Receptionist> receptionists = new ArrayList<Receptionist>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(unique = true, nullable = false)
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}

	@Temporal(TemporalType.DATE)
	public Date getRegistrationDate() { return registrationDate; }
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Drug> getDrugs() { return drugs; }
	public void setDrugs(List<Drug> drugs) { this.drugs = drugs; }

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Receptionist> getReceptionists() { return this.receptionists;}
	public void setReceptionists( List<Receptionist> receptionists) {this.receptionists = receptionists;}
}
