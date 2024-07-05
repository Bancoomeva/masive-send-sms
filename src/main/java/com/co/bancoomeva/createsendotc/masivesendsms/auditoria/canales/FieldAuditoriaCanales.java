package com.co.bancoomeva.createsendotc.masivesendsms.auditoria.canales;

public class FieldAuditoriaCanales {

	private String messageId;
	private String invokerDateTime;
	private String ipTransaccion;
	private String codTransaccion;
	private String canal;
	private String usuario;
	private String action;

	public String getAction() {
		return action;
	}

	public String getCanal() {
		return canal;
	}

	public String getCodTransaccion() {
		return codTransaccion;
	}

	public String getInvokerDateTime() {
		return invokerDateTime;
	}

	public String getIpTransaccion() {
		return ipTransaccion;
	}

	public String getMessageId() {
		return messageId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}

	public void setInvokerDateTime(String invokerDateTime) {
		this.invokerDateTime = invokerDateTime;
	}

	public void setIpTransaccion(String ipTransaccion) {
		this.ipTransaccion = ipTransaccion;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "FieldAuditoria [messageId=" + messageId + ", invokerDateTime=" + invokerDateTime + ", ipTransaccion="
				+ ipTransaccion + ", codTransaccion=" + codTransaccion + ", canal=" + canal + ", usuario=" + usuario
				+ ", action=" + action + "]";
	}

}
