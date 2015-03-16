package com.suwadi.web.flash.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.suwadi.web.flash.Flash;

@Component("flash")
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class FlashImpl implements Flash {
	private Map<String, Object> messages = new HashMap<String, Object>();

	public void info(String messageKey, String... params) {
		messages.put("flash_info", messageKey);
		messages.put("flash_info_params", params);
	}

	public void error(String messageKey, String... params) {
		messages.put("flash_error", messageKey);
		messages.put("flash_error_params", params);
	}

	public void success(String messageKey, String... params) {
		messages.put("flash_success", messageKey);
		messages.put("flash_success_params", params);
	}

	public Map<String, Object> getMessages() {
		return this.messages;
	}

	public void reset() {
		this.messages = new HashMap<String, Object>();
	}

}