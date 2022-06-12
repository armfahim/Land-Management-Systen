package com.odcl.lms.setup.enumeration;

public enum PaymentMethod {
	Cash("Cash"),
	Bank("Bank"),
	Bond("Bond");
	
	private final String displayName;
	
	PaymentMethod(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
