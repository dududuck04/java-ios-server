package com.cbm.saekalpi.config.utils;

public class BaseException extends Exception {
	private BaseResponseStatus status;
	
	public BaseException(BaseResponseStatus status) {
		this.status = status;
	}

	public BaseResponseStatus getStatus() {
		return this.status;
	}

	@Override
	public String getMessage() {
		return this.status != null ? this.status.getMessage(): null;
	}
}
