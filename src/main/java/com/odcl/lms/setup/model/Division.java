package com.odcl.lms.setup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "geo_divisions")
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "json_id")
public class Division extends BaseModel {

	@Column(name = "name", length = 255, nullable = true)
	private String name;

	@Column(name = "geo_code", length = 255, nullable = true)
	private String geoCode;

	@Column(name = "latitude", length = 255, nullable = true)
	private String latitude;

	@Column(name = "longitude", length = 255, nullable = true)
	private String longitude;

//	@OneToMany(mappedBy = "division", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<District> district = new ArrayList<>();

	private String name_bn;

	private String url;

	@Column(name = "is_active", length = 256, nullable = true, columnDefinition = "boolean default true")
	private Boolean isActive;

//	@OneToMany(mappedBy = "division", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Requisition> requisition = new ArrayList<>();
}
