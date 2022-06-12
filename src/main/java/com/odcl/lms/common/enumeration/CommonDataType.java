package com.odcl.lms.common.enumeration;

public enum CommonDataType {

	Company("Company"),
	Department("Department"),
	Designation("Designation"),
	LandPurchaseStatus("Land Purchase Status"),
	OwnerShipType("Ownership Type"),
	PaymentType("Payment Type");
	
	
	private final String displayName;

	CommonDataType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
