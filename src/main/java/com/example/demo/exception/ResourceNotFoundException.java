package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fildName;
	long fildValue;
	public ResourceNotFoundException(String resourceName, String fildName, long fildValue) {
		super(String.format("%s user not found with %s : %s",resourceName,fildName,fildValue));
		this.resourceName = resourceName;
		this.fildName = fildName;
		this.fildValue = fildValue;
		
}
}
