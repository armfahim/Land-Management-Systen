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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "json_id")
@EqualsAndHashCode(callSuper = false)
public class ProcessLayer extends BaseModel {

	@Column(name = "process_name", length = 64, nullable = false)
	private String processName;

	@Column(name = "SLUG", length = 64, nullable = false)
	private String SLUG;

	@Column(name = "sort_order", length = 16, nullable = false)
	private Integer sortOrder;

	@Column(name = "is_active", length = 256, nullable = true, columnDefinition = "boolean default true")
	private Boolean isActive;
}
