package com.stacksimplify.restservices.exceptions;

import java.util.Date;

public class CustomErrorDetails {
	private Date timestamp;
	private String custommessage;
	private String errordetails;

	public CustomErrorDetails(Date timestamp, String custommessage, String errordetails) {
		super();
		this.timestamp = timestamp;
		this.custommessage = custommessage;
		this.errordetails = errordetails;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getCustommessage() {
		return custommessage;
	}

	public String getErrordetails() {
		return errordetails;
	}
	
}
