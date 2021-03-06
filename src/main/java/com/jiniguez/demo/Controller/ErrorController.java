package com.jiniguez.demo.Controller;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jiniguez.demo.DTO.ErrorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;


@ControllerAdvice(basePackages = { "com.jiniguez.demo.Controller" })
public class ErrorController {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO error(NotFoundException e) {
		return new ErrorDTO(404, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO error(Exception e) {
		return new ErrorDTO(404, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(ParseException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorDTO error(ParseException e) {
		return new ErrorDTO(409, e.getMessage());
	}
}