package com.co.bancoomeva.createsendotc.masivesendsms.model;

public class StatusResponse {

	private int statusCode;
	private String statusmessage;

	public StatusResponse() {

	}

	public StatusResponse(int statusCode, String statusmessage) {
		this.statusCode = statusCode;
		this.statusmessage = statusmessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusmessage() {
		return statusmessage;
	}

	public void setStatusmessage(String statusmessage) {
		this.statusmessage = statusmessage;
	}

	@Override
	public String toString() {
		return "StatusResponse [statusCode=" + statusCode + ", statusmessage=" + statusmessage + "]";
	}

}
