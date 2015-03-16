package com.suwadi.utils.app;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
	private static Properties properties = new Properties();
	static {
		try {
			properties.load(ApplicationProperties.class
					.getResourceAsStream("/config/app/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return ApplicationProperties.properties.getProperty(key);
	}
}
