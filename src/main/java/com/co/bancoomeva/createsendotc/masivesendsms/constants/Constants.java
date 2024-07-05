package com.co.bancoomeva.createsendotc.masivesendsms.constants;

public class Constants {

	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String APPLICATION_JSON = "application/json";
	public static final String URL_SOURCE = "URL_SOURCE";
	public static final String AUTHENTICATION = "AUTHENTICATION";

	public static final String DESTINO_INVALID = "Invalid recipient";
	public static final String TEXT_EXCEEDS = "Message exceeds 160 characters.";
	public static final String URL_DOMAIN_EXCEEDS = "Domain short url parameter exceeds 50 characters.";
	public static final String URL_EXCEEDS = "Url parameter exceeds 1000 characters.";
	public static final String URL_EMPTY = "The required url parameter is missing.";
	public static final String VALID_URL = "SHORTURL";
	public static final String DOMAIN_EMPTY = "Url or domain url parameters are not valid";
	public static final String URL_NOT_VALID = "Replacement field short url is not correct";

	public static final String REGEX_MAIN = "^(http|https)://([a-zA-Z0-9]+\\.)+[a-zA-Z]{2,}(/{0,1}[^\\s]*)?$";

	public static final String ERROR_SHORT_URL_CONFIG = "the field shortUrlConfig cant be declare";

	public static final int isLongMessage = 160;
	public static final int CARACTER_URL = 1000;
	public static final Integer CARACTER_DOMAIN = 50;

}
