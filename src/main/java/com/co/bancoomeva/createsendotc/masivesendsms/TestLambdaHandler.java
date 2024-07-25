package com.co.bancoomeva.createsendotc.masivesendsms;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent.ProxyRequestContext;

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
				+ "      \"url\":\"https://www.youtube.com\",\r\n"
			//	+ "      \"url\":\"asdutube.com\",\r\n"
				+ "      \"domainShorturl\":\"http://ma.sv/\"\r\n"
				+ "   }\r\n"
				+ "}";
		
	//	795f7a0d-9417-4c1a-bbd9-78c3142f93ea

		
		
		
		String body2 = "{\r\n"
				+ " \"headers\":{ "
				+ "   \"request-date-time\":\"2024-07-22T11:38:13\",\r\n"			
				+ "   \"channel\":\"NP_1_1\",\r\n"					
				+ "   \"request-id\":\"e47a6701-cc67-4e17-a756-884be6b49cd1\",\r\n"			
				+ "   \"ip-terminal\":\"10.11.60.68\",\r\n"			
				+ "   \"user-login\":\"Cfalcao\"\r\n"
				+ "},"		
				
				+ "   \"to\":\"573222428392\",\r\n"				
				+ "   \"text\":\"Hola Cielo Mensaje de prueba, esta es una url\",\r\n"
				+ "   \"customdata\":\"CUS_ID_0125\",\r\n"
				+ "   \"isPremium\":false,\r\n"
				+ "   \"isFlash\":false,\r\n"
				+ "   \"isLongmessage\":false,\r\n"
				+ "   \"isRandomRoute\":false\r\n"				
				+ "}";
		
			
		
		APIGatewayProxyRequestEvent request= new APIGatewayProxyRequestEvent();
		LambdaHandler lambdaHandler = new LambdaHandler ();
		
		
//		headers.put("messageId", "");
//		headers.put("invokerDateTime", "2024-05-14T10:38:13");
//		headers.put("ipTransaccion", "2001:0ca8:85a3:0000:0000:8a2e:0370:7334");
//		headers.put("codTransaccion", "test");
//		headers.put("canal", "0");
//		headers.put("usuario", "test");
		
		
		request.setBody(body2);		
//		request.setHeaders(headers);
		
		ProxyRequestContext requestContext = new ProxyRequestContext();
		
		requestContext.setRequestId("sasd-asdasd-asdas-dasasd");
		requestContext.setAccountId("asdas-asdasd-asda-");
		requestContext.setApiId("ass");
	
		
		
		
		APIGatewayProxyResponseEvent response = lambdaHandler.handleRequest(request, null);
		
		
		System.out.println("////////////////////////////////////////////");
		System.out.println(response.getStatusCode());		
		System.out.println(response.getBody());
		 
	}
}
