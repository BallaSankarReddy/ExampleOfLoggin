package com.springboot.exception;

public class Error {

	private String message;

	public Error(String message) {
		super();
		this.message = message;
	}

	public Error() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
