package com.odcl.lms.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.odcl.lms.common.enumeration.CommonDataType;
import com.odcl.lms.setup.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonData extends BaseModel {

	@Column(name = "common_data_code", nullable = true)
	private String code;

	@Column(name = "common_data_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private CommonDataType commonDataType;

	@Column(name = "label_name", nullable = false)
	private String labelName;

	@Column(name = "sort_order", nullable = true)
	private String sortOrder;

	@Column(columnDefinition = "boolean default true")
	private Boolean isActive;

}
