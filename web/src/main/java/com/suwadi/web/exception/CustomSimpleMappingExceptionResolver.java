package com.suwadi.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.suwadi.message.email.ExceptionMailer;

public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	private ExceptionMailer exceptionMailer;

	@Autowired
	public void setExceptionMailer(ExceptionMailer exceptionMailer) {
		this.exceptionMailer = exceptionMailer;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		try {
			this.exceptionMailer.notifyException(ex);
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return super.doResolveException(request, response, handler, ex);
	}

}
