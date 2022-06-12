package com.odcl.lms.purchase.enumeration;

public enum PurchaseRequisitionStatus {
	Draft("Draft"),
	Submited("Submited"),
	Rejected("Rejected"),
	Approved("Approved"),
	Registered("Registered"),
	Completed("Completed");
	
	private final String displayName;

	PurchaseRequisitionStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
