package com.app.customexceptionhandler;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
