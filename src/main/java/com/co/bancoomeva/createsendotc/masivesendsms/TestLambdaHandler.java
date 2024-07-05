package com.co.bancoomeva.createsendotc.masivesendsms;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class TestLambdaHandler {

	public static void main(String[] args) {
	
		
		
		String body = "{\r\n"
				
				+ "   \"messageId\":\"LFMSGNLL23_longMessage\",\r\n"
				+ "   \"invokerDateTime\":\"2024-05-14T10:38:13\",\r\n"
				+ "   \"ipTransaccion\":\"10.11.60.68\",\r\n"
				+ "   \"codTransaccion\":\"931001\",\r\n"
				+ "   \"canal\":\"1\",\r\n"
				+ "   \"usuario\":\"LRivas\",\r\n"								
				+ "   \"action\":\"57312dsf445885165\",\r\n"
				
								
				+ "   \"to\":\"573222428392\",\r\n"				
				+ "   \"text\":\"Hola Cielo Mensaje de prueba, esta es una url: SHORTURL\",\r\n"
				+ "   \"customdata\":\"CUS_ID_0125\",\r\n"
				+ "   \"isPremium\":false,\r\n"
				+ "   \"isFlash\":false,\r\n"
				+ "   \"isLongmessage\":false,\r\n"
				+ "   \"isRandomRoute\":false,\r\n"
				+ "   \"shortUrlConfig\":{\r\n"
				+ "      \"url\":\"https://www.youtube.com\",\r\n"
				+ "      \"domainShorturl\":\"http://ma.sv/\"\r\n"
				+ "   }\r\n"
				+ "}";
		
		

		
		
		
		String body2 = "{\r\n"
				
				+ "   \"messageId\":\"LFMSGNLL23_longMessage\",\r\n"
				+ "   \"invokerDateTime\":\"2024-05-14T10:38:13\",\r\n"
				+ "   \"ipTransaccion\":\"10.11.60.68\",\r\n"
				+ "   \"codTransaccion\":\"931001\",\r\n"
				+ "   \"canal\":\"1\",\r\n"
				+ "   \"usuario\":\"LRivas\",\r\n"								
				+ "   \"action\":\"57312dsf445885165\",\r\n"				
								
				+ "   \"to\":\"573222428392\",\r\n"				
				+ "   \"text\":\"Hola Cielo Mensaje de prueba, esta es una url: SHORTURL\",\r\n"
				+ "   \"customdata\":\"CUS_ID_0125\",\r\n"
				+ "   \"isPremium\":false,\r\n"
				+ "   \"isFlash\":false,\r\n"
				+ "   \"isLongmessage\":false,\r\n"
				+ "   \"isRandomRoute\":false,\r\n"
				+ "   \"shortUrlConfig\":{\r\n"
				+ "      \"url\":\"https://www.youtsdfsdfsdfsdfgsdfdfgsdfgsdfgdsfube.com\",\r\n"	
				+ "      \"domainShorturl\":\"https://www.youtsdfsdfsdfsdfgsdfdfgsdfgsdfgdsfube.com\"\r\n"	
				
				+ "   }\r\n"
				+ "}";
		
		APIGatewayProxyRequestEvent request= new APIGatewayProxyRequestEvent();
		LambdaHandler lambdaHandler = new LambdaHandler ();
		
		
		request.setBody(body2);		
		
		
		APIGatewayProxyResponseEvent response = lambdaHandler.handleRequest(request, null);
		
		
		System.out.println("////////////////////////////////////////////");
		System.out.println(response.getStatusCode());		
		System.out.println(response.getBody());
		 
	}
}
