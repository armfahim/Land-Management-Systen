package com.odcl.lms.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private String roleName; // roleName = authority

	public Role() {
	}

	public Role(String authority, String description) {
		this.description = description;
		this.setRoleName(authority);
	}
}
