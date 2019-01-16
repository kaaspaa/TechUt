package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "receptionist.all", query = "Select r from Receptionist r")
})
public class Receptionist {
	private Long id;

	private String firstName;
	private String lastName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

}
