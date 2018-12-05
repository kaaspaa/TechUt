package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "drug.availability", query = "Select d from Drug d where d.availability = true")
})
public class Drug {

	private Long id;
	private String name;
	private String barCode;
	private Boolean availability = true;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public Boolean getAvailability() { return availability; }

	public void setAvailability(Boolean availability) { this.availability = availability; }

	@Column(unique = true, nullable = false)
	public String getBarCode() { return barCode; }

	public void setBarCode(String barCode) { this.barCode = barCode; }
}
