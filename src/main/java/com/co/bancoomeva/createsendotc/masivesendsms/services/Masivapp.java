package com.co.bancoomeva.createsendotc.masivesendsms.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.APPLICATION_JSON;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.HEADER_AUTHORIZATION;
import static com.co.bancoomeva.createsendotc.masivesendsms.constants.Constants.HEADER_CONTENT_TYPE;

import static com.co.bancoomeva.auditoria.auditoria_canales.constants.Commons.NON_FOUND;

public class Masivapp {

	public HttpResponse<String> sendMessage(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, String url,
			String credentials) throws Exception {

		// LOGGER.info("Method: Invoking sendMessage");

		// LOGGER.info("Method: Services Invoked " + url);

		HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(url)).version(HttpClient.Version.HTTP_2)
				.setHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON).setHeader(HEADER_AUTHORIZATION, credentials)
				.POST(HttpRequest.BodyPublishers.ofString(apiGatewayProxyRequestEvent.getBody())).build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest,
				HttpResponse.BodyHandlers.ofString());

		noFound(response);

		// LOGGER.info("Method: Finished sendMessage");

		return response;

	}

	private void noFound(HttpResponse<String> httpRequest) throws Exception {
		if (httpRequest.statusCode() == NON_FOUND) {
			// LOGGER.info("Method: sendMessage {url no found}");
			throw new Exception();
		}
	}

}
