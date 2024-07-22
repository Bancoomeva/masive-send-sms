package com.co.bancoomeva.createsendotc.masivesendsms.model;

public class HeadersAuditoria {

	private String   request_date_time;
	private String channel;
	private String request_id;
	private String ip_terminal;
	private String user_login;
	
	public String getRequest_date_time() {
		return request_date_time;
	}
	public void setRequest_date_time(String request_date_time) {
		this.request_date_time = request_date_time;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getIp_terminal() {
		return ip_terminal;
	}
	public void setIp_terminal(String ip_terminal) {
		this.ip_terminal = ip_terminal;
	}
	public String getUser_login() {
		return user_login;
	}
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	@Override
	public String toString() {
		return "HeadersRequest [request_date_time=" + request_date_time + ", channel=" + channel + ", request_id="
				+ request_id + ", ip_terminal=" + ip_terminal + ", user_login=" + user_login + "]";
	}
	
}
