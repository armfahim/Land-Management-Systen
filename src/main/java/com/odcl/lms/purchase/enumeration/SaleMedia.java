package com.odcl.lms.purchase.enumeration;

public enum SaleMedia {
	Broker("Broker"), 
	Self("Self");

	private final String displayName;

	SaleMedia(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
