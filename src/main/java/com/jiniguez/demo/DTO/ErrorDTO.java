package com.jiniguez.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorDTO implements Serializable{

	public ErrorDTO(int i, String message) {
		super();
		code = i; msg = message;
	}

	private static final long serialVersionUID = 7187152228951614135L;
	private Integer code;

	private String msg;
}
