package com.odcl.lms.setup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class DagArea extends BaseModel {

	@Column(name = "latitude", precision = 2, nullable = true)
	private Double latitude;

	@Column(name = "longitude", precision = 2, nullable = true)
	private Double longitude;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dag_info_id")
	private DagInformation dagInfo;
}
