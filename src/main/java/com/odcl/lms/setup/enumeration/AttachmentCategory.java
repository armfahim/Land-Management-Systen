package com.odcl.lms.setup.enumeration;

public enum AttachmentCategory {
	Mutation("Mutation"), 
	DCR("DCR"), 
	KhajnaReceipt("Khajna receipt"),
	DeedOfCompany("Deed of company"),
	ViaDeed("Via_deed"),
	Khatian("Khatian");

	private final String displayName;

	AttachmentCategory(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
