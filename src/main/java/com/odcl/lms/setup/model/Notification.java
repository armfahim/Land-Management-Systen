package com.odcl.lms.setup.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odcl.lms.auth.model.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Notification extends BaseModel {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String notificationText;

	@Column(name = "url", length = 255, nullable = false)
	private String url;

	private Boolean isRead;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd h:mm:ss a")
	private LocalDateTime timeStamp;

}
