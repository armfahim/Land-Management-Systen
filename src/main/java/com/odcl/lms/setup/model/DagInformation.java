package com.odcl.lms.setup.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "json_id")
public class DagInformation extends BaseModel {

	@Column(name = "dag_number", length = 256, nullable = true)
	private String dagNumber;

	@Column(name = "record_quantity", nullable = true)
	private Double recordQuantity;

	@Column(name = "govt_la", nullable = true)
	private Double govtLa;

	@Column(name = "rest_land_after_la", nullable = true)
	private Double restLandAfterLa;

	@Column(name = "total_pre_bought", nullable = true)
	private Double totalPreBought;

	@Column(name = "available_land", nullable = true)
	private Double availableLand;

	@Column(name = "is_active", length = 256, nullable = true, columnDefinition = "boolean default true")
	private Boolean isActive;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mouza_id")
	private Mouza mouza;
}
