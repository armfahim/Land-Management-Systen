package com.odcl.lms.setup.enumeration;

public enum PurchaserType {
	Person("Person"), 
	Company("Company");

	private final String displayName;

	PurchaserType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
