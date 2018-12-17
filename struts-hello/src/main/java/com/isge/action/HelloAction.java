package com.isge.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport{
	private String message;
	@Override
	public String execute() throws Exception {
		final Logger logger = LogManager.getLogger("HelloWorld");
		logger.info("Hello world");
		setMessage("ISGE dev team");;
		return SUCCESS;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
