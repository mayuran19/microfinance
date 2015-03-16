package com.suwadi.aop.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suwadi.domain.User;
import com.suwadi.message.email.UserMailer;

@Aspect
@Component("afterUserCreation")
public class AfterUserCreation {
	private UserMailer userMailer;

	public UserMailer getUserMailer() {
		return userMailer;
	}

	@Autowired
	public void setUserMailer(UserMailer userMailer) {
		this.userMailer = userMailer;
	}

	@AfterReturning(pointcut = "execution(* com.suwadi.service.UserService.saveOrUpdate(..))", returning = "user")
	public void sendWelcomeEmail(Object user) {
		System.out.println("Huyyaaaaaa, AOP is working");
		if (user != null && user instanceof User) {
			System.out
					.println("user is not null and will be sending out the mail");
			User u = (User) user;
			userMailer.sendConfirmationEmail(u);
		} else {
			System.out.println("this is not correctly returning user object");
		}
	}
}
