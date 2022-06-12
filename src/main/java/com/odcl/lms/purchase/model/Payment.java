package com.odcl.lms.purchase.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odcl.lms.common.enumeration.CommonDataType;
import com.odcl.lms.setup.enumeration.PaymentMethod;
import com.odcl.lms.setup.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Payment extends BaseModel {
	
	@Column(name = "payment_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private CommonDataType commonDataType;
	
	@Column(precision = 4,nullable = true)
	private Double amount;
	
	@Column(name="payment_date")
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;
	
	@Column(name = "payment_method", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Column(columnDefinition = "TEXT")
	private String remarks;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="purchase__info_id")
	private PurchaseInfo purchaseInfo;

}
