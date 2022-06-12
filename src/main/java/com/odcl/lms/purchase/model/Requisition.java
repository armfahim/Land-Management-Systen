package com.odcl.lms.purchase.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odcl.lms.auth.model.User;
import com.odcl.lms.purchase.enumeration.PurchaseRequisitionStatus;
import com.odcl.lms.purchase.enumeration.SaleMedia;
import com.odcl.lms.setup.model.BaseModel;
import com.odcl.lms.setup.model.DagInformation;
import com.odcl.lms.setup.model.District;
import com.odcl.lms.setup.model.Division;
import com.odcl.lms.setup.model.KhatianType;
import com.odcl.lms.setup.model.LandType;
import com.odcl.lms.setup.model.Mouza;
import com.odcl.lms.setup.model.ProcessLayer;
import com.odcl.lms.setup.model.Purchaser;
import com.odcl.lms.setup.model.Thana;

import lombok.Getter;
import lombok.Setter;

/**
 * @author A.R.M. Fahim
 * @since March 06, 2022
 */
@Entity
@Setter
@Getter

public class Requisition extends BaseModel {

	// Requisition Info
	@Column(name = "requisition_no", length = Integer.MAX_VALUE, nullable = true)
	private Long requisitionNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "requistion_created_at", nullable = true)
	private Date reqDate;

	// Broker Info
	@Column(name = "sale_media", length = 16, nullable = true)
	@Enumerated(EnumType.STRING)
	private SaleMedia saleMedia;

	@Column(name = "broker_name", length = 128, nullable = true)
	private String brokerName;

	@Column(name = "contact_number", length = 16, nullable = true)
	private String contactNumber;

	@Column(name = "broker_nid", length = 16, nullable = true)
	private String brokerNid;

	// Land Info
	@Column(name = "offering_land", nullable = true)
	private Double offeringLand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "division_id", nullable = true)
	private Division division;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id", nullable = true)
	private District district;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "thana_id", nullable = true)
	private Thana thana;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mouza_id", nullable = true)
	private Mouza mouza;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "khatian_type_id", nullable = true)
	private KhatianType khatianType;

	@Column(name = "khatian_no", nullable = true, length = 64)
	private String khatianNo;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "dag_info_id", nullable = true)
	private DagInformation dagInfo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "land_type_id", nullable = true)
	private LandType landType;

	@Column(name = "selling_qty", nullable = true)
	private Double sellingQty;

	@Column(name = "govt_mouza_rate", nullable = true)
	private Double govtMouzaRate;

	@Column(name = "offering_price", nullable = true)
	private Double offeringPrice;

	@Column(columnDefinition = "TEXT", name = "source_remarks")
	private String sourceRemarks;

	// Legal Info
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchaser_id")
	private Purchaser purchaser;

	@Column(name = "purchasable_qty", nullable = true)
	private Double purchasableQty;

//	private String checklist;

	@Column(name = "legal_remarks", columnDefinition = "TEXT", nullable = true)
	private String legal_remarks;

	// Surveyor Info
	@Column(name = "noksha_qty", nullable = true)
	private Double nokshaQty;

	@Column(name = "survey_qty", nullable = true)
	private Double surveyQty;

	@Column(name = "mouza_map_marking_attachment", nullable = true)
	private String mouzaMapMarkingAttachment;

	@Column(name = "google_map_marking_attachment", nullable = true)
	private String googleMapMarkingAttachment;

	@Column(columnDefinition = "TEXT", name = "surveyor_remarks")
	private String surveyorRemarks;

	// Account Info
	@Column(name = "actual_value", nullable = true)
	private Double actualValue;

	@Column(name = "deed_value", nullable = true)
	private Double deedValue;

	@Column(name = "development_value", nullable = true)
	private Double developmentValue;

	@Column(name = "percentage", nullable = true, length = 64)
	private Double percentage;

	@Column(name = "reg_cost_office", nullable = true)
	private Double regCostOffice;

	@Column(name = "misc_cost_office", nullable = true)
	private Double miscCostOffice;

	@Column(name = "reg_cost_govt", nullable = true)
	private Double regCostGovt;

	@Column(name = "misc_cost_govt", nullable = true)
	private Double miscCostGovt;

	@Column(name = "account_remarks", columnDefinition = "TEXT", nullable = true)
	private String accountRemarks;

	// Management Info
	@Column(name = "management_remarks", columnDefinition = "TEXT", nullable = true)
	private String managementRemarks;

	// Land Owner
	//@OneToMany(mappedBy = "requisition", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private List<LandOwner> landOwner = new ArrayList<>();

	// Purchase Info
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_info_id", nullable = true)
	private PurchaseInfo purchaseInfo;

	// Process History
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "present_layer_id", nullable = true)
	private ProcessLayer presentLayer;

	@Column(name = "present_status", length = 64, nullable = true)
	@Enumerated(EnumType.STRING)
	private PurchaseRequisitionStatus presentStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "from_user_id", nullable = true)
	private User fromUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "present_user_id", nullable = true)
	private User presentUser;

	@Column(name = "process_round", length = Integer.MAX_VALUE, nullable = true)
	private Integer processRound;

	@Column(name = "is_back", columnDefinition = "boolean default false", nullable = true)
	private Boolean isBack;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "back_from_layer_id", nullable = true)
	private ProcessLayer backFrom;

}
