package com.hsd.devops.common.exception;

import com.hsd.devops.core.exception.DevOpsException;


public class BussinessException extends DevOpsException {

	public BussinessException(BizExceptionEnum bizExceptionEnum) {
		super(bizExceptionEnum.getCode(), bizExceptionEnum.getMessage(), bizExceptionEnum.getUrlPath());
	}
}
