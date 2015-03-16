package com.suwadi.web.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.message.email.ExceptionMailer;

@Controller
public class ExceptionsController {
	private ExceptionMailer exceptionMailer;

	@Autowired
	public void setExceptionMailer(ExceptionMailer exceptionMailer) {
		this.exceptionMailer = exceptionMailer;
	}

	@RequestMapping(value = { "/common/exceptions" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		Exception e = (Exception) request.getAttribute("exception");
		System.out.println(e);
		try {
			this.exceptionMailer.notifyException(e);
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return "common/error";
	}

	@RequestMapping(value = { "/errors/404.html" }, method = RequestMethod.GET)
	public String error404(HttpServletRequest request) {
		String uri = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		Exception e = new Exception("Invalid URL: " + uri);
		request.setAttribute("exception", e);
		this.exceptionMailer.notifyException(e);

		return "common/error";
	}
}
