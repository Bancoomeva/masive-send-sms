package com.co.bancoomeva.createsendotc.masivesendsms;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class TestLambdaHandler {

	public static void main(String[] args) {
	
		
		
		String body = "{\r\n"								
				+ "   \"to\":\"573222428392\",\r\n"				
				+ "   \"text\":\"Hola Cielo Mensaje de prueba, esta es una url: SHORTURL\",\r\n"
				+ "   \"customdata\":\"CUS_ID_0125\",\r\n"
				+ "   \"isPremium\":false,\r\n"
				+ "   \"isFlash\":false,\r\n"
				+ "   \"isLongmessage\":false,\r\n"
				+ "   \"isRandomRoute\":false,\r\n"
				+ "   \"shortUrlConfig\":{\r\n"
			//	+ "      \"url\":\"https://www.youtube.com\",\r\n"
				+ "      \"url\":\"asdutube.com\",\r\n"
				+ "      \"domainShorturl\":\"http://ma.sv/\"\r\n"
				+ "   }\r\n"
				+ "}";
		
		

		
		
		
		String body2 = "{\r\n"
											
				+ "   \"to\":\"573222428392\",\r\n"				
				+ "   \"text\":\"Hola Cielo Mensaje de prueba, esta es una url\",\r\n"
				+ "   \"customdata\":\"CUS_ID_0125\",\r\n"
				+ "   \"isPremium\":false,\r\n"
				+ "   \"isFlash\":false,\r\n"
				+ "   \"isLongmessage\":false,\r\n"
				+ "   \"isRandomRoute\":false\r\n"				
				+ "}";
		
		
		
		Map<String, String> headers = new HashMap<String, String>();
		
	
		
		APIGatewayProxyRequestEvent request= new APIGatewayProxyRequestEvent();
		LambdaHandler lambdaHandler = new LambdaHandler ();
		
		
		headers.put("messageId", "test");
		headers.put("invokerDateTime", "2024-05-14T10:38:13");
		headers.put("ipTransaccion", "2001:0ca8:85a3:0000:0000:8a2e:0370:7334");
		headers.put("codTransaccion", "test");
		headers.put("canal", "0");
		headers.put("usuario", "test");
		
		
		request.setBody(body);		
		request.setHeaders(headers);
		
		APIGatewayProxyResponseEvent response = lambdaHandler.handleRequest(request, null);
		
		
		System.out.println("////////////////////////////////////////////");
		System.out.println(response.getStatusCode());		
		System.out.println(response.getBody());
		 
	}
}
