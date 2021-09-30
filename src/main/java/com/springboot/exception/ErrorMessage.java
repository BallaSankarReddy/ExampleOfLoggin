package com.springboot.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ErrorMessage {

	private String errorMessage;
	private String documentationType;
	private Integer errorCode;
	private List<Error> errors;

	public ErrorMessage(String errorMessage, Integer errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public ErrorMessage(List<Error> errors) {
		super();
		this.errors = errors;
	}

	public ErrorMessage(String errorMessage, String documentation, Integer errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.documentationType = documentation;
		this.errorCode = errorCode;
	}

	public ErrorMessage(String documentation, Integer errorCode, List<Error> errors) {
		super();
		this.documentationType = documentation;
		this.errorCode = errorCode;
		this.errors = errors;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDocumentation() {
		return documentationType;
	}

	public void setDocumentation(String documentation) {
		this.documentationType = documentation;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
