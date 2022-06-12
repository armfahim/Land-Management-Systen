package com.odcl.lms.purchase.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.odcl.lms.setup.model.BaseModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "json_id")
@EqualsAndHashCode(callSuper = false)
public class PurchaseInfo extends BaseModel {

	@Column(name = "purchase_no", length = 255, nullable = true)
	private String purchaseNo;

	@Column(name = "purchase_date", nullable = true)
	private LocalDate purchaseDate;

	@Column(name = "attachment_path", length = 255, nullable = true)
	private String attachmentPath;

	@Column(name = "is_active", length = 256, nullable = true, columnDefinition = "boolean default true")
	private Boolean isActive;

	@Column(name = "remarks", columnDefinition = "TEXT", nullable = true)
	private String remarks;
}
