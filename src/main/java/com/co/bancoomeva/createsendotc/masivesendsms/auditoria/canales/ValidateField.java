package com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales;

import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.FIELD;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.FIELD_CHANNEL;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.FIELD_COD_TRANSACTION;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.FIELD_INVOKER_DATE_TIME;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.FIELD_IP_TRANSACTION;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.FIELD_MESSAGE_ID;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.FIELD_USER;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACConstants.MSN_FIELD_ERROR;

import java.util.Optional;

public class ValidateField {

	public void validateFieldAuditoriaCanales(FieldAuditoriaCanales auditoriaCanales) throws InputValidationException {
			
		validateFieldNull(FIELD_MESSAGE_ID, Optional.ofNullable(auditoriaCanales.getMessageId()));
		validateFieldNull(FIELD_INVOKER_DATE_TIME, Optional.ofNullable(auditoriaCanales.getInvokerDateTime()));
		validateFieldNull(FIELD_IP_TRANSACTION, Optional.ofNullable(auditoriaCanales.getIpTransaccion()));
		validateFieldNull(FIELD_COD_TRANSACTION, Optional.ofNullable(auditoriaCanales.getCodTransaccion()));
		validateFieldNull(FIELD_CHANNEL, Optional.ofNullable(auditoriaCanales.getCanal()));
		validateFieldNull(FIELD_USER, Optional.ofNullable(auditoriaCanales.getUsuario()));		
	
	}

	public void validateFieldNull(String date, Optional<String> value) throws InputValidationException {
		if (value.isEmpty()) {
			throw new InputValidationException(MSN_FIELD_ERROR.replace(FIELD, date));
		}
	}

}
