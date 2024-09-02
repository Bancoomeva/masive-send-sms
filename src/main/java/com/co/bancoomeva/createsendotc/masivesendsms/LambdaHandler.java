package com.co.bancoomeva.createsendotc.masivesendsms;

import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.MSN_FILED_NUMBER_ERROR;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.MSN_FILED_URL_ERROR;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.MSG_BODY_NULL;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.FINISHED;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.FIELD;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.INIT;

import static com.co.bancoomeva.auditoria.auditoria_canales.constants.HttpStatusCode.UNAUTHORIZED;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.HttpStatusCode.BAD_REQUETS;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.HttpStatusCode.OK;

import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Constants.REGEX_POSITIVE_NUMBER;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Constants.REGEX_URL;

import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.ERROR_SHORT_URL_CONFIG;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_DOMAIN_EXCEEDS;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.CARACTER_DOMAIN;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.AUTHENTICATION;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.TEXT_EXCEEDS;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.CARACTER_URL;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.APPLICATION;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_EXCEEDS;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.MESSAGE_SMS;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_SOURCE;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.VALID_URL;


import java.net.http.HttpResponse;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import com.co.bancoomeva.auditoria.auditoria_canales.AuditoriaCanales;
import com.co.bancoomeva.auditoria.auditoria_canales.ValidateField;
import com.co.bancoomeva.auditoria.auditoria_canales.exceptio.InputValidationException;
import com.co.bancoomeva.auditoria.auditoria_canales.log.Log;
import com.co.bancoomeva.auditoria.auditoria_canales.model.Header;

import com.co.bancoomeva.createsendotc.masivesendsms.constants.Environment;
import com.co.bancoomeva.createsendotc.masivesendsms.model.MessageRequest;
import com.co.bancoomeva.createsendotc.masivesendsms.model.MessageResponse;
import com.co.bancoomeva.createsendotc.masivesendsms.services.Masivapp;

import com.co.bancoomeva.auditoria.auditoria_canales.services.StatusResponseServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LambdaHandler.class);

	private Gson Gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting()	.setFieldNamingStrategy(field -> field.getName().replace("_", "-")).create();

	private StatusResponseServices statusResponseServices = new StatusResponseServices();
	private APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent;
	private ValidateField validateField = new ValidateField();
	private Header headers;

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent,
			Context context) {

		LOGGER.debug("Invoque method handleRequest: " + INIT);
		LOGGER.info(Log.generateLog(apiGatewayProxyRequestEvent, context, MESSAGE_SMS, APPLICATION));

		try {

			headers = Gson.fromJson(apiGatewayProxyRequestEvent.getBody(), Header.class);
			new AuditoriaCanales().validateFieldAuditoriaCanales(headers);

			MessageRequest messageRequest = Gson.fromJson(apiGatewayProxyRequestEvent.getBody(), MessageRequest.class);
			validateInputField(messageRequest);

			HttpResponse<String> response = new Masivapp().sendMessage(apiGatewayProxyRequestEvent,	new Environment().getEnv(URL_SOURCE), new Environment().getEnv(AUTHENTICATION));

			switch (response.statusCode()) {
			case OK:
				apiGatewayProxyResponseEvent = statusResponseServices.successful(response.body());
				break;
			case BAD_REQUETS:
				apiGatewayProxyResponseEvent = statusResponseServices.badRequest(Gson.fromJson(response.body(), MessageResponse.class).getStatusMessage(),headers.getHeaders().getRequest_id());
				break;
			case UNAUTHORIZED:
				apiGatewayProxyResponseEvent = statusResponseServices.unauthorized(headers.getHeaders().getRequest_id());
				break;
			default:
				apiGatewayProxyResponseEvent = statusResponseServices.internalServerError(headers.getHeaders().getRequest_id());
			}

		} catch (InputValidationException e) {
			apiGatewayProxyResponseEvent = statusResponseServices.badRequest(e.getMessage(), headers.getHeaders().getRequest_id());
		} catch (Exception e) {
			LOGGER.error(e.toString());
			apiGatewayProxyResponseEvent = statusResponseServices.internalServerError(headers.getHeaders().getRequest_id());
		}

		LOGGER.debug("Invoque method handleRequest: " + FINISHED);

		return apiGatewayProxyResponseEvent;
	}

	private void validateInputField(MessageRequest messageRequest) throws InputValidationException {

		LOGGER.debug("Invoque method validateInputField: " + INIT);

		if (messageRequest != null) {

			validateField.validateFieldNull("to", Optional.ofNullable(messageRequest.getTo()));
			validateField.validateRegEx(messageRequest.getTo(), REGEX_POSITIVE_NUMBER, MSN_FILED_NUMBER_ERROR.replace(FIELD, "to"));
			validateField.validateFieldNull("text", Optional.ofNullable(messageRequest.getText()));
			validateField.validateFieldNull("customdata", Optional.ofNullable(messageRequest.getCustomdata()));
			validateField.validateSize(messageRequest.getText(), 160, TEXT_EXCEEDS);

			if (messageRequest.getText().contains(VALID_URL)) {

				if (messageRequest.getShortUrlConfig() != null) {

					validateField.validateFieldNull("url", Optional.ofNullable(messageRequest.getShortUrlConfig().getUrl()));
					validateField.validateSize(messageRequest.getShortUrlConfig().getUrl(), CARACTER_URL, URL_EXCEEDS);
					validateField.validateFieldNull("domainShorturl", Optional.ofNullable(messageRequest.getShortUrlConfig().getDomainShorturl()));
					validateField.validateSize(messageRequest.getShortUrlConfig().getDomainShorturl(), CARACTER_DOMAIN,	URL_DOMAIN_EXCEEDS);
					validateField.validateRegEx(messageRequest.getShortUrlConfig().getUrl(), REGEX_URL,	MSN_FILED_URL_ERROR.replace(FIELD, "url"));

				} else {
					throw new InputValidationException(ERROR_SHORT_URL_CONFIG);
				}
			}
		} else {
			throw new InputValidationException(MSG_BODY_NULL);
		}

		LOGGER.debug("Invoque method handleRequest: " + FINISHED);
	}

}
