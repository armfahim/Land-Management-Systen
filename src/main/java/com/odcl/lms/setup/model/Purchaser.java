package com.odcl.lms.setup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.odcl.lms.setup.enumeration.PurchaserType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author A.R.M. Fahim Mar 6, 2022
 */
@Entity
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "json_id")
public class Purchaser extends BaseModel {

	@Column(name = "full_name", length = 128, nullable = false)
	String fullName;

	@Column(name = "purchaser_type", length = 128, nullable = true)
	@Enumerated(EnumType.STRING)
	PurchaserType purchaserType;

	@Column(columnDefinition = "boolean default false")
	Boolean isActive;
}
