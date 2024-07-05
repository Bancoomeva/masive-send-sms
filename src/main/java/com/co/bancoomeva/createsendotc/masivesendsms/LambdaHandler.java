package com.co.bancoomeva.createsendotc.masivesendsms;

import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACCommons.BAD_REQUETS;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACCommons.MSG_BODY_NULL;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACCommons.OK;
import static com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ACCommons.UNAUTHORIZED;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.AUTHENTICATION;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.CARACTER_DOMAIN;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.CARACTER_URL;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.DOMAIN_EMPTY;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.TEXT_EXCEEDS;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_DOMAIN_EXCEEDS;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_EMPTY;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_EXCEEDS;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_NOT_VALID;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.URL_SOURCE;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.VALID_URL;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.isLongMessage;

import java.net.http.HttpResponse;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.FieldAuditoriaCanales;
import com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.InputValidationException;
import com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales.ValidateField;
import com.co.bancoomeva.createsendotc.masivesendsms.constants.Common;
import com.co.bancoomeva.createsendotc.masivesendsms.constants.Environment;
import com.co.bancoomeva.createsendotc.masivesendsms.model.MessageRequest;
import com.co.bancoomeva.createsendotc.masivesendsms.model.MessageResponse;
import com.co.bancoomeva.createsendotc.masivesendsms.services.Masivapp;
import com.co.bancoomeva.createsendotc.masivesendsms.services.StatusResponseServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LambdaHandler.class);

	private Gson Gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

	private StatusResponseServices statusResponseServices = new StatusResponseServices();

	private APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent;

	private ValidateField validateField = new ValidateField();

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent,
			Context context) {

		LOGGER.info("iniciando");

		LOGGER.info(apiGatewayProxyRequestEvent.toString());

		try {

			MessageRequest messageRequest = Gson.fromJson(apiGatewayProxyRequestEvent.getBody(), MessageRequest.class);
			validateField.validateFieldAuditoriaCanales(
					Gson.fromJson(apiGatewayProxyRequestEvent.getBody(), FieldAuditoriaCanales.class));

			validateInputField(messageRequest);

			HttpResponse<String> response;

			response = new Masivapp().sendMessage(apiGatewayProxyRequestEvent, new Environment().getEnv(URL_SOURCE),
					new Environment().getEnv(AUTHENTICATION));

			System.out.println(response.statusCode());

			switch (response.statusCode()) {
			case OK:
				apiGatewayProxyResponseEvent = statusResponseServices.successful(response.body());
				break;
			case BAD_REQUETS:
				apiGatewayProxyResponseEvent = statusResponseServices
						.badRequest(Gson.fromJson(response.body(), MessageResponse.class).getStatusMessage());
				break;
			case UNAUTHORIZED:
				apiGatewayProxyResponseEvent = statusResponseServices.unauthorized();
				break;
			default:
				apiGatewayProxyResponseEvent = statusResponseServices.internalServerError();
			}

		} catch (InputValidationException e) {
			apiGatewayProxyResponseEvent = statusResponseServices.badRequest(e.getMessage());
		} catch (Exception e) {
			System.out.println(e);
			apiGatewayProxyResponseEvent = statusResponseServices.internalServerError();
		}

		return apiGatewayProxyResponseEvent;
	}

	private void validateInputField(MessageRequest messageRequest) throws InputValidationException {

		if (messageRequest != null) {

			validateField.validateFieldNull("to", Optional.ofNullable(messageRequest.getTo()));
			validateField.validateFieldNull("text", Optional.ofNullable(messageRequest.getText()));
			validateField.validateFieldNull("customdata", Optional.ofNullable(messageRequest.getCustomdata()));

			if (messageRequest.getText().length() > isLongMessage) {
				throw new InputValidationException(TEXT_EXCEEDS);
			}
			if (messageRequest.getText().contains(VALID_URL)) {
				if (messageRequest.getShortUrlConfig().getUrl() == null
						|| messageRequest.getShortUrlConfig().getUrl().isEmpty()) {
					throw new InputValidationException(URL_EMPTY);
				} else if (messageRequest.getShortUrlConfig().getDomainShorturl() == null
						|| messageRequest.getShortUrlConfig().getDomainShorturl().isEmpty()) {
					throw new InputValidationException(DOMAIN_EMPTY);
				} else {
					if (messageRequest.getShortUrlConfig().getUrl().length() > CARACTER_URL) {
						throw new InputValidationException(URL_EXCEEDS);
					} else if (messageRequest.getShortUrlConfig().getDomainShorturl().length() > CARACTER_DOMAIN) {
						throw new InputValidationException(URL_DOMAIN_EXCEEDS);
					}
				}
			}
			boolean urlvalid = new Common().validaUrl(messageRequest.getShortUrlConfig().getUrl());
			if (urlvalid) {
				throw new InputValidationException(URL_NOT_VALID);
			}

		} else {
			throw new InputValidationException(MSG_BODY_NULL);
		}

	}

}
