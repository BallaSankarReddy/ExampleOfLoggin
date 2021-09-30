package com.springboot.exception;

public class BusinessValidationException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3255426360947579691L;

	private ErrorMessage errorMessage;

	public BusinessValidationException(ErrorMessage errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public BusinessValidationException() {
		super();
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

}
