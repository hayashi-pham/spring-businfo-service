package com.lampnc.businfo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.lampnc.businfo.NotFoundException;

@RestController
@ControllerAdvice
public class ExceptionHandlerController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@Autowired
	private ErrorAttributes errorAttributes;

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { NotFoundException.class })
	public ExceptionHandlerMessage notFoundErrorHandler(Exception e) {
		return new ExceptionHandlerMessage(e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	public ExceptionHandlerMessage argumentErrorHandler(Exception e) {
		return new ExceptionHandlerMessage("The request is invalid.");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public ExceptionHandlerMessage defaultErrorHandler(Exception e) {
		return new ExceptionHandlerMessage(e.getClass().getSimpleName(), e.getMessage());
	}

	@RequestMapping(value = "/error")
	public ExceptionHandlerMessage fallbackErrorHandler(HttpServletRequest request, HttpServletResponse response) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> attributes = errorAttributes.getErrorAttributes(requestAttributes, false);
		return new ExceptionHandlerMessage(response.getStatus(), attributes);
	}

}
