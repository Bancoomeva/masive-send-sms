package com.co.bancoomeva.createsendotc.masivesendsms.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

	
	
	
	public boolean validaUrl(String mensaje) {
		String toregx = "^(http|https)://([a-zA-Z0-9]+\\.)+[a-zA-Z]{2,}(/{0,1}[^\\s]*)?$";
		Pattern pattern = Pattern.compile(toregx);
		Matcher matcher = pattern.matcher(mensaje);
		
		if (matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}
}
