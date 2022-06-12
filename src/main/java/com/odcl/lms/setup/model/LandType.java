package com.odcl.lms.setup.model;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class LandType extends BaseModel {

	@Column(name = "land_type_name", length = 256, nullable = true)
	private String landTypeName;

	@Column(name = "land_type_code", length = 256, nullable = true)
	private String landTypecode;

	@Column(name = "is_active", length = 256, nullable = true, columnDefinition = "boolean default true")
	private Boolean isActive;

//	@OneToMany(mappedBy = "landType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Requisition> requisition = new ArrayList<>();
}
