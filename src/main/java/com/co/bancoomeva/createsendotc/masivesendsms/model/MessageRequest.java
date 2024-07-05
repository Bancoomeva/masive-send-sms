package com.co.bancoomeva.createsendotc.masivesendsms.model;

public class MessageRequest {

	private String to;
	private String text;
	private String customdata;
	private String isPremium;
	private String isFlash;
	private String isLongmessage;
	private String isRandomRoute;
	private RequestShortUrlConfig shortUrlConfig;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCustomdata() {
		return customdata;
	}

	public void setCustomdata(String customdata) {
		this.customdata = customdata;
	}

	public String getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(String isPremium) {
		this.isPremium = isPremium;
	}

	public String getIsFlash() {
		return isFlash;
	}

	public void setIsFlash(String isFlash) {
		this.isFlash = isFlash;
	}

	public String getIsLongmessage() {
		return isLongmessage;
	}

	public void setIsLongmessage(String isLongmessage) {
		this.isLongmessage = isLongmessage;
	}

	public String getIsRandomRoute() {
		return isRandomRoute;
	}

	public void setIsRandomRoute(String isRandomRoute) {
		this.isRandomRoute = isRandomRoute;
	}

	public RequestShortUrlConfig getShortUrlConfig() {
		return shortUrlConfig;
	}

	public void setShortUrlConfig(RequestShortUrlConfig shortUrlConfig) {
		this.shortUrlConfig = shortUrlConfig;
	}

	@Override
	public String toString() {
		return "MessageRequest [to=" + to + ", text=" + text + ", customdata=" + customdata + ", isPremium=" + isPremium
				+ ", isFlash=" + isFlash + ", isLongmessage=" + isLongmessage + ", isRandomRoute=" + isRandomRoute
				+ ", shortUrlConfig=" + shortUrlConfig + "]";
	}

}
