package com.co.bancoomeva.createsendotc.masivesendsms.services;

import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.BAD_REQUETS;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.MSG_INTERNAL_SERVER_ERROR;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.MSG_UNAUTHORIZED;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.OK;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.SCODE_INTERNAL_SERVER_ERROR;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.UNAUTHORIZED;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.co.bancoomeva.createsendotc.masivesendsms.model.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatusResponseServices {

	private APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent;
	private Gson Gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

	public APIGatewayProxyResponseEvent internalServerError() {
		apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
		apiGatewayProxyResponseEvent
				.setBody(Gson.toJson(new StatusResponse(SCODE_INTERNAL_SERVER_ERROR, MSG_INTERNAL_SERVER_ERROR)));
		apiGatewayProxyResponseEvent.setStatusCode(SCODE_INTERNAL_SERVER_ERROR);
		return apiGatewayProxyResponseEvent;
	}

	public APIGatewayProxyResponseEvent badRequest(String statusmessage) {
		apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
		apiGatewayProxyResponseEvent.setBody(Gson.toJson(new StatusResponse(BAD_REQUETS, statusmessage)));
		apiGatewayProxyResponseEvent.setStatusCode(BAD_REQUETS);
		return apiGatewayProxyResponseEvent;
	}

	public APIGatewayProxyResponseEvent unauthorized() {
		apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
		apiGatewayProxyResponseEvent.setBody(Gson.toJson(new StatusResponse(UNAUTHORIZED, MSG_UNAUTHORIZED)));
		apiGatewayProxyResponseEvent.setStatusCode(UNAUTHORIZED);
		return apiGatewayProxyResponseEvent;
	}

	public APIGatewayProxyResponseEvent successful(String body) {
		apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
		apiGatewayProxyResponseEvent.setBody(body);
		apiGatewayProxyResponseEvent.setStatusCode(OK);
		return apiGatewayProxyResponseEvent;
	}

}
