package com.suwadi.dao.hibernate.events;

import java.io.Serializable;

import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultUpdateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import com.suwadi.domain.User;
import com.suwadi.utils.string.RandomStringGenerator;

@Component("userUpdateEventListener")
public class UserUpdateEventListener extends DefaultUpdateEventListener {
	private Md5PasswordEncoder passwordEncoder;
	private RandomStringGenerator randomStringGenerator;

	public Md5PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Autowired
	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public RandomStringGenerator getRandomStringGenerator() {
		return randomStringGenerator;
	}

	@Autowired
	public void setRandomStringGenerator(
			RandomStringGenerator randomStringGenerator) {
		this.randomStringGenerator = randomStringGenerator;
	}

	@Override
	protected Serializable performSaveOrUpdate(SaveOrUpdateEvent event) {
		if (event.getObject() instanceof User) {
			User user = (User) event.getObject();
			System.out.println(user);
			if (user.getPassword() != null && user.getConfirmPassword() != null
					&& !user.getPassword().isEmpty()
					&& !user.getConfirmPassword().isEmpty()
					&& user.getPassword().equals(user.getConfirmPassword())) {
				// set the salt for the first time only
				user.setSalt(randomStringGenerator.getRandomSalt());
				// if the password is set in the user object, change the
				// password
				// password is transient field, only to keep the plainPassword
				user.setHashedPassword(passwordEncoder.encodePassword(
						user.getPassword(), user.getSalt()));

			}

		}
		return super.performSaveOrUpdate(event);
	}

}
