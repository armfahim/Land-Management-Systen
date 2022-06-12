package com.odcl.lms.setup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class AttachmentInformation extends BaseModel {

	@Column(name = "file_path", length = 255, nullable = false)
	private String filePath;

	@Column(name = "attachment_title", length = 128, nullable = false)
	private String title;

	@ManyToOne
	@JoinColumn(name = "attachment_title_id")
	private AttachmentTitle attachmentTitle;
}
