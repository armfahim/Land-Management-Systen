package com.odcl.lms.setup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.odcl.lms.setup.enumeration.AttachmentCategory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
public class AttachmentTitle extends BaseModel {

	@Column(name = "attachment_title", length = 128, nullable = false)
	private String attachemtTitle;

	@Column(name = "attachement_category", nullable = true)
	@Enumerated(EnumType.STRING)
	private AttachmentCategory category;

	private Boolean isActive;
}
