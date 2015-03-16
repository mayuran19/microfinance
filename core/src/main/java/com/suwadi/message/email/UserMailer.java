package com.suwadi.message.email;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.suwadi.domain.User;
import com.suwadi.utils.spring.CustomPropertyPlaceholderConfigurer;

@Component("userMailer")
public class UserMailer implements MessageSourceAware {
	private JavaMailSender javaMailSender;
	private VelocityEngine velocityEngine;
	private CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer;
	private MessageSource messageSource;

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public CustomPropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
		return propertyPlaceholderConfigurer;
	}

	@Autowired
	public void setPropertyPlaceholderConfigurer(
			CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer) {
		this.propertyPlaceholderConfigurer = propertyPlaceholderConfigurer;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void sendConfirmationEmail(final User user) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				System.out.println("preparing the email");
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setSubject(messageSource.getMessage(
						"user.confirmation.subject", null, Locale.US));
				message.setTo(user.getProfile().getEmail());
				message.setFrom(getPropertyPlaceholderConfigurer().getProperty(
						"app.email.from"));

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				String html = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "/velocity/email/confirmationEmail.vm",
						model);
				message.setText(html, true);
			}
		};
		this.javaMailSender.send(preparator);
	}

}
