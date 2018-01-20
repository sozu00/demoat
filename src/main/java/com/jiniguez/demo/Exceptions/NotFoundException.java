package com.jiniguez.demo.Exceptions;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -8426858989268610314L;

	private static final String MSG = "La entidad buscada no existe";

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super(MSG);
	}	
}
