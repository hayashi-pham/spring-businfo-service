package com.lampnc.businfo.controller;

import java.util.Date;
import java.util.Map;

public class ExceptionHandlerMessage implements java.io.Serializable {

	private String source;
	private String message;
	private Date date;

	public ExceptionHandlerMessage(String message) {
		this(null, message, new Date());
	}

	public ExceptionHandlerMessage(String source, String message) {
		this(source, message, new Date());
	}

	public ExceptionHandlerMessage(String source, String message, Date date) {
		this.source = source;
		this.message = message;
		this.date = date;
	}

	public ExceptionHandlerMessage(int status, Map<String, Object> attributes) {
		this.source = "HTTP Status " + status;
		this.message = attributes.get("error") + " - " + attributes.get("message");
		this.date = (Date) attributes.get("timestamp");
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
