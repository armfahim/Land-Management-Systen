package com.odcl.lms.purchase.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odcl.lms.purchase.enumeration.SaleMedia;
import com.odcl.lms.purchase.model.LandOwner;
import com.odcl.lms.setup.model.DagInformation;
import com.odcl.lms.setup.model.District;
import com.odcl.lms.setup.model.Division;
import com.odcl.lms.setup.model.KhatianType;
import com.odcl.lms.setup.model.LandType;
import com.odcl.lms.setup.model.Mouza;
import com.odcl.lms.setup.model.Thana;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequisitionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	// Requisition Info
	private Integer requisitionNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = ISO.DATE)
	private String reqDate;

	// Broker Info
	private SaleMedia saleMedia;

	private String brokerName;

	private String contactNumber;

	private String brokerNid;

	// Land Info
	private Double offeringLand;

	private Division division;

	private District district;

	private Thana thana;

	private Mouza mouza;

	private KhatianType khatianType;

	private String khatianNo;

	private DagInformation dagInfo;

	private LandType landType;

	private Double sellingQty;

	private Double govtMouzaRate;

	private Double offeringPrice;

	private String sourceRemarks;

	// Legal Info
	/*
	 * private Purchaser purchaser;
	 * 
	 * private Double purchasableQty;
	 * 
	 * private String legal_remarks;
	 */

	// Surveyor Info
	/*
	 * private Double nokshaQty;
	 * 
	 * private Double surveyQty;
	 * 
	 * private String mouzaMapMarkingAttachment;
	 * 
	 * private String googleMapMarkingAttachment;
	 * 
	 * private String surveyorRemarks;
	 */

	// Account Info
	/*
	 * private Double actualValue;
	 * 
	 * private Double deedValue;
	 * 
	 * private Double developmentValue;
	 * 
	 * private Double percentage;
	 * 
	 * private Double regCostOffice;
	 * 
	 * private Double miscCostOffice;
	 * 
	 * private Double regCostGovt;
	 * 
	 * private Double miscCostGovt;
	 * 
	 * private String accountRemarks;
	 */

	// Management Info
//	private String managementRemarks;

//	 Land Owner
	private List<LandOwner> landOwner = new ArrayList<>();

	// Process History
	/*
	 * private ProcessLayer presentLayer;
	 * 
	 * private User fromUser;
	 * 
	 * private User presentUser;
	 * 
	 * private Boolean isBack;
	 * 
	 * private ProcessLayer backFrom;
	 */
}
