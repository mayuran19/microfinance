package com.suwadi.message.email;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.suwadi.service.ConfigurationService;

@Component("exceptionMailer")
public class ExceptionMailer {
	private ApplicationMailSender applicationMailSender;
	private ConfigurationService configurationService;

	@Autowired
	public void setApplicationMailSender(
			ApplicationMailSender applicationMailSender) {
		this.applicationMailSender = applicationMailSender;
	}

	@Autowired
	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ApplicationMailSender getApplicationMailSender() {
		return applicationMailSender;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void notifyException(final Exception e) {
		e.printStackTrace(System.out);
		String notification = this.configurationService
				.findValueByKey("application.exception.notification");
		if (notification != null && notification.equalsIgnoreCase("true")) {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					System.out.println("preparing the email");
					MimeMessageHelper message = new MimeMessageHelper(
							mimeMessage);

					message.setSubject("[MF App error] " + e.getMessage());
					message.setTo("mayuran19@gmail.com");
					message.setFrom("mayuran19@gmail.com");

					StringWriter sw = new StringWriter();
					e.printStackTrace(new PrintWriter(sw));

					message.setText(sw.toString(), true);
				}
			};
			this.applicationMailSender.send(preparator);
		}

	}
}
