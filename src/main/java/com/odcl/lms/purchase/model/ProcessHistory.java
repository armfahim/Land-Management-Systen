package com.odcl.lms.purchase.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odcl.lms.auth.model.User;
import com.odcl.lms.setup.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
public class ProcessHistory extends BaseModel {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "from_user_id")
	private User fromUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "to_user_id")
	private User toUser;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd h:mm:ss a")
	@Column(name = "process_time_stamp", updatable = false)
	private LocalDateTime processTimeStamp;

	@Column(columnDefinition = "TEXT")
	private String remarks;
}
