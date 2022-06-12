package com.odcl.lms.auth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.odcl.lms.setup.model.BaseModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "json_id")
@NoArgsConstructor
@Table(name = "lms_user")
public class User extends BaseModel {

	@Column(length = 128, nullable = false)
	@NotBlank
	private String userName;

	@Column(length = 512, nullable = false)
	@NotBlank
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Column(columnDefinition = "boolean default false")
	private Boolean accountExpired;

	@Column(columnDefinition = "boolean default false")
	private Boolean accountLocked;

	@Column(columnDefinition = "boolean default false")
	private Boolean credentialsExpired;

	@Column(length = 32, nullable = false)
	private String employeeId;

	@Column(length = 128, nullable = false)
	private String fullName;

	@Column(length = 64, nullable = false)
	private String officeTitle;

	@Column(length = 32, nullable = false)
	private String designation;

	@Column(length = 32, nullable = true)
	private String department;

	@Column(length = 32, nullable = false, unique = true)
	private String mobileNo;

	@Column(length = 32, nullable = false)
	private String layer;

	@Column(length = 32, nullable = true, unique = true)
	@Email
	private String email;

	private String photoPath;

	private Boolean isLead;

	private Boolean isActive;

	public User(String username, String email, String password) {
		this.userName = username;
		this.email = email;
		this.password = password;
	}

}
