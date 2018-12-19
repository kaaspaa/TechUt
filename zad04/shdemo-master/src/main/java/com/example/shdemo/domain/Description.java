package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "description.all", query = "Select ds from Description")
})
public class Description {
	private Long id;
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getDescription() { return  description; }
	public void setDescription(String description) { this.description = description; }

}
