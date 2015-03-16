package com.suwadi.web.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.suwadi.web.flash.Flash;

public class FlashInterceptor implements WebRequestInterceptor {
	private Flash flash;

	@Autowired
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	public void preHandle(WebRequest request) throws Exception {
		final Map<String, Object> messages = flash.getMessages();
		if (messages.containsKey("flash_info")) {
			request.setAttribute("flash_info", messages.get("flash_info"),
					RequestAttributes.SCOPE_REQUEST);
			request.setAttribute("flash_info_params",
					messages.get("flash_info_params"),
					RequestAttributes.SCOPE_REQUEST);
		}
		if (messages.containsKey("flash_error")) {
			request.setAttribute("flash_error", messages.get("flash_error"),
					RequestAttributes.SCOPE_REQUEST);
			request.setAttribute("flash_error_params",
					messages.get("flash_error_params"),
					RequestAttributes.SCOPE_REQUEST);
		}
		if (messages.containsKey("flash_success")) {
			request.setAttribute("flash_success",
					messages.get("flash_success"),
					RequestAttributes.SCOPE_REQUEST);
			request.setAttribute("flash_success_params",
					messages.get("flash_success_params"),
					RequestAttributes.SCOPE_REQUEST);
		}
		flash.reset();
	}

	public void postHandle(WebRequest request, ModelMap model) throws Exception {

	}

	public void afterCompletion(WebRequest request, Exception ex)
			throws Exception {
	}

}
