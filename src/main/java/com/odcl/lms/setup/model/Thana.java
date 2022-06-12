package com.odcl.lms.setup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@Table(name = "thana")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "json_id")
public class Thana extends BaseModel {

	@Column(name = "geo_code", length = 255, nullable = true)
	private String geoCode;

	@Column(name = "lat", length = 255, nullable = true)
	private String latitude;

	@Column(name = "lon", length = 255, nullable = true)
	private String longitude;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id")
	private District district;

	private String name;
	private String name_bn;
	private String url;

	@Column(name = "is_active", length = 256, nullable = true, columnDefinition = "boolean default true")
	private Boolean isActive;

}
