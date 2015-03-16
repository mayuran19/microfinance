package com.suwadi.message.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.suwadi.service.ConfigurationService;

@Component("applicationMailSender")
public class ApplicationMailSender extends JavaMailSenderImpl {
	private ConfigurationService configurationService;

	@Autowired
	public ApplicationMailSender(ConfigurationService configurationService) {
		System.out.println(configurationService);
		this.configurationService = configurationService;
		this.setJavaMailProperties(this.getApplicationJavaMailProperties());
		this.setProtocol(this.configurationService
				.findValueByKey("app.email.protocol"));
		this.setHost(this.configurationService.findValueByKey("app.email.host"));
		this.setPort(Integer.valueOf(this.configurationService
				.findValueByKey("app.email.port")));
		this.setUsername(this.configurationService
				.findValueByKey("app.email.username"));
		this.setPassword(this.configurationService
				.findValueByKey("app.email.password"));
	}

	public Properties getApplicationJavaMailProperties() {
		Properties javaMailProperties = new Properties();
		String mailSmtpAuth = this.configurationService
				.findValueByKey("mail.smtp.auth");
		String mailSmtpStarttlsEnable = this.configurationService
				.findValueByKey("mail.smtp.starttls.enable");
		String mailSmtpQuitwait = this.configurationService
				.findValueByKey("mail.smtp.quitwait");

		if (mailSmtpAuth != null && !mailSmtpAuth.isEmpty())
			javaMailProperties.setProperty("mail.smtp.auth", mailSmtpAuth);
		if (mailSmtpStarttlsEnable != null && !mailSmtpStarttlsEnable.isEmpty())
			javaMailProperties.setProperty("mail.smtp.starttls.enable",
					mailSmtpStarttlsEnable);
		if (mailSmtpQuitwait != null && !mailSmtpQuitwait.isEmpty())
			javaMailProperties.setProperty("mail.smtp.quitwait",
					mailSmtpQuitwait);

		return javaMailProperties;
	}

}
