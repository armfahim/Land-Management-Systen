package com.odcl.lms.purchase.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.odcl.lms.setup.model.BaseModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "json_id")
public class LandOwner extends BaseModel {

	@Column(name = "owner_name", nullable = false, length = 64)
	private String ownerName;

	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

	@Column(name = "age", nullable = false, length = 16)
	private Integer age;

	@Column(name = "owned_land", nullable = false)
	private Double ownedLand;

	@Column(name = "attorney", nullable = false, length = 64)
	private String attorney;

	@Column(name = "nid", nullable = false, length = 64)
	private String nid;

	@Column(name = "photo_path", nullable = false, length = 255)
	private String photoPath;

	@Column(name = "ownership_type", nullable = false, length = 64)
	private String ownershipType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "req_id")
	private Requisition requisition;

	@Column(name = "total_records")
	private Long totalRecords;

}
