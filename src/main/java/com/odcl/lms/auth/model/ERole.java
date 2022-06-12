package com.odcl.lms.auth.model;

public enum ERole {

	ROLE_USER("ROLE_USER"), 
	ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN"),
	ROLE_ADMIN("ROLE_ADMIN");


	private final String displayName;
	
	ERole(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
