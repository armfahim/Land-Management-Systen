package com.odcl.lms.auth.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

	public static String getLoginUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return authentication.getName(); // current username
		} else {
			return null;
		}
	}

	public static boolean isLogged() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return null != authentication && !("anonymousUser").equals(authentication.getName());
	}
}
