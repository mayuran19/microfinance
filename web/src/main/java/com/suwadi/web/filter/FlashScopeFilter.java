package com.suwadi.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

public class FlashScopeFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			Map<String, ?> flash = (Map<String, ?>) httpSession
					.getAttribute(FlashScope.FLASH_SCOPE_ATTRIBUTE);
			if (flash != null) {
				for (Map.Entry<String, ?> entry : flash.entrySet()) {
					Object currentValue = request.getAttribute(entry.getKey());
					if (currentValue == null) {
						request.setAttribute(entry.getKey(), entry.getValue());
					}
				}
				httpSession.removeAttribute(FlashScope.FLASH_SCOPE_ATTRIBUTE);
			}
		}
		filterChain.doFilter(request, response);
	}

}