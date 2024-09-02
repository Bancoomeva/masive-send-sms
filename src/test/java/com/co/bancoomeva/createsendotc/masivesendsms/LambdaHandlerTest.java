package com.co.bancoomeva.createsendotc.masivesendsms;

import java.net.http.HttpResponse;

import org.junit.Test;
import org.mockito.Mockito;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.co.bancoomeva.createsendotc.masivesendsms.services.Masivapp;

public class LambdaHandlerTest {

	private static final String EXPECTED_REQUIEST_ID = "c89d07ed-ea4b-4090-a15e-587ebd57216c";
	private static final String URL = "test url";
	private static final String CREDENTIALS = "test credentials";

	@Test
	public void handleRequestTestHappyPath() throws Exception {
		APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent();
		Context context = Mockito.mock(Context.class);
		Masivapp masivapp = Mockito.mock(Masivapp.class);
		HttpResponse<String> httpResponse = Mockito.mock(HttpResponse.class);
		Mockito.when(context.getAwsRequestId()).thenReturn(EXPECTED_REQUIEST_ID);
		Mockito.when(masivapp.sendMessage(apiGatewayProxyRequestEvent, URL, CREDENTIALS)).thenReturn(httpResponse);
		Mockito.when(httpResponse.statusCode()).thenReturn(200);
		Mockito.when(httpResponse.body()).thenReturn("200");

		LambdaHandler LambdaHandler = new LambdaHandler();

		String body = "{\r\n" + " \"headers\":{ " + "   \"request-date-time\":\"2024-07-22T11:38:13\",\r\n"
				+ "   \"channel\":\"NP_1_1\",\r\n" + "   \"request-id\":\"e47a6701-cc67-4e17-a756-884be6b49cd1\",\r\n"
				+ "   \"ip-terminal\":\"10.11.60.68\",\r\n" + "   \"user-login\":\"Cfalcao\"\r\n" + "},"

				+ "   \"to\":\"573222428392\",\r\n"
				+ "   \"text\":\"Hola Cielo Mensaje de prueba, esta es una url\",\r\n"
				+ "   \"customdata\":\"CUS_ID_0125\",\r\n" + "   \"isPremium\":false,\r\n" + "   \"isFlash\":false,\r\n"
				+ "   \"isLongmessage\":false,\r\n" + "   \"isRandomRoute\":false\r\n" + "}";

		apiGatewayProxyRequestEvent.setBody(body);

		APIGatewayProxyResponseEvent response = LambdaHandler.handleRequest(apiGatewayProxyRequestEvent, context);

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());

	}

}
