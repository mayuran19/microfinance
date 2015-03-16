package com.suwadi.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.suwadi.domain.User;
import com.suwadi.service.UserService;

@Service("customAuthenticationManager")
public class CustomAuthenticationManager implements AuthenticationManager {
	// password + "{" + salt.toString() + "}";
	private UserService userService;
	private Md5PasswordEncoder passwordEncoder;

	@Autowired
	public CustomAuthenticationManager(UserService userService,
			Md5PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		User user = null;
		if (authentication.getName() == null
				|| "".equals(authentication.getName())) {
			throw new BadCredentialsException("Username is required");
		}
		user = userService.findByUserName(authentication.getName());
		if (user == null) {
			throw new BadCredentialsException("User does not exist");
		}

		if (passwordEncoder.isPasswordValid(user.getHashedPassword(),
				(String) authentication.getCredentials(), user.getSalt()) == false) {
			throw new BadCredentialsException("Wrong password!");
		}
		// Here's the main logic of this custom authentication manager
		// Username and password must be the same to authenticate
		if (authentication.getName().equals(authentication.getCredentials()) == true) {
			throw new BadCredentialsException(
					"Entered username and password are the same!");
		} else {
			return new UsernamePasswordAuthenticationToken(user,
					authentication.getCredentials(),
					user.getGrantedAuthorities());
		}
	}

}
