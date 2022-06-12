package com.odcl.lms.setup.model;

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
public class Mouza extends BaseModel {

	@Column(name = "name", length = 255, nullable = true)
	private String name;

	@Column(name = "geo_code", length = 255, nullable = true)
	private String geoCode;

	@Column(name = "latitude", length = 255, nullable = true)
	private String latitude;

	@Column(name = "longitude", length = 255, nullable = true)
	private String longitude;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "thana_id")
	private Thana thana;

	@Column(name = "is_active", length = 256, nullable = true, columnDefinition = "boolean default true")
	private Boolean isActive;
}
