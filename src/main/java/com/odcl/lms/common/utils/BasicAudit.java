package com.odcl.lms.common.utils;

import java.time.LocalDateTime;

import com.odcl.lms.auth.utils.UserUtil;
import com.odcl.lms.setup.model.BaseModel;

public abstract class BasicAudit {

	public static Object setAttributeForCreateUpdate(Object object, String activeOperation) {
		if (activeOperation.equalsIgnoreCase("create")) {
			((BaseModel) object).setCreateDateTime(LocalDateTime.now());
			((BaseModel) object).setCreatedBy(UserUtil.getLoginUser());
		} else if (activeOperation.equalsIgnoreCase("update")) {
			((BaseModel) object).setUpdateDateTime(LocalDateTime.now());
			((BaseModel) object).setUpdatedBy(UserUtil.getLoginUser());
		}
		return object;
	}

}
