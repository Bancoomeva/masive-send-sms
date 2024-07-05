package com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales;

import java.util.List;

public class InputValidationException extends Exception {
    
	private static final long serialVersionUID = -1606736426457863046L;

	private List<String> errorMessageList;

	public InputValidationException(String shortMessage) {
		super(shortMessage);
	}

	public InputValidationException(String shortMessage, List<String> errorMessageList) {
		super(shortMessage);
		this.errorMessageList = errorMessageList;
	}

	public List<String> getErrorMessageList() {
		return errorMessageList;
	}
}
