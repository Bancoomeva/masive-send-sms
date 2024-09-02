package com.co.bancoomeva.createsendotc.masivesendsms.services;

import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.FINISHED;
import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.INIT;

import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.HEADER_AUTHORIZATION;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.HEADER_CONTENT_TYPE;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.APPLICATION_JSON;

import static com.co.bancoomeva.auditoria.auditoria_canales.constants.HttpStatusCode.NON_FOUND;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class Masivapp {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(Masivapp.class);

	
	public HttpResponse<String> sendMessage(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, String url,
			String credentials) throws Exception {

		LOGGER.debug("Invoque method sendMessage: " + INIT);

		HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(url)).version(HttpClient.Version.HTTP_2)
				.setHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON).setHeader(HEADER_AUTHORIZATION, credentials)
				.POST(HttpRequest.BodyPublishers.ofString(apiGatewayProxyRequestEvent.getBody())).build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest,
				HttpResponse.BodyHandlers.ofString());
		
		LOGGER.debug("HttpResponse response: " + response);
		
		noFound(response);

		LOGGER.debug("Invoque method sendMessage: " + FINISHED);

		return response;

	}

	private void noFound(HttpResponse<String> httpRequest) throws Exception {
		if (httpRequest.statusCode() == NON_FOUND) {
			LOGGER.error("URL NO FOUND");
			throw new Exception();
		}
	}

}
