package com.suwadi.web.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomSavedRequestAwareAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	protected final Logger logger = Logger.getLogger(this.getClass());
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			String targetUrl = "/";
			Collection<GrantedAuthority> authorities = authentication
					.getAuthorities();
			if (authorities.size() > 0) {
				GrantedAuthority[] arrayAuthority = {};
				GrantedAuthority authority = authorities
						.toArray(arrayAuthority)[0];
				if (authority.getAuthority().equals("ROLE_ROOT")) {
					targetUrl = "/root";
				} else if (authority.getAuthority().equals("ROLE_ADMIN")) {
					targetUrl = "/admin";
				} else if (authority.getAuthority().equals("ROLE_ACCOUNTANT")) {
					targetUrl = "/accountant";
				} else if (authority.getAuthority().equals(
						"ROLE_ADMIN_FIELD_OFFICER")) {
					targetUrl = "/adminFieldOfficer";
				} else if (authority.getAuthority()
						.equals("ROLE_FIELD_OFFICER")) {
					targetUrl = "/fieldOfficer";
				}
			}

			// Use the DefaultSavedRequest URL
			logger.info("Redirecting to DefaultSavedRequest Url: " + targetUrl);
			getRedirectStrategy().sendRedirect(request, response, targetUrl);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
