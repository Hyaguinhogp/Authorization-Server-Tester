package com.test.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthCustomError {

	private String error;
	
	@JsonProperty(value = "description_error")
	private String descriptionError;
	
	public OAuthCustomError() {
	}

	public OAuthCustomError(String error, String descriptionError) {
		super();
		this.error = error;
		this.descriptionError = descriptionError;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescriptionError() {
		return descriptionError;
	}

	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}
}
