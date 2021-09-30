package com.springboot.exception;

public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorResponseMessage;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String errorResponseMessage) {
		super();
		this.errorResponseMessage = errorResponseMessage;
	}

	public String getErrorResponseMessage() {
		return errorResponseMessage;
	}

	public void setErrorResponseMessage(String errorResponseMessage) {
		this.errorResponseMessage = errorResponseMessage;
	}

}
