package com.suwadi.utils.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private Map<String, String> propertiesMap = new HashMap<String, String>();

	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		for (Entry<Object, Object> entry : props.entrySet()) {
			propertiesMap.put(entry.getKey().toString(), entry.getValue()
					.toString());
		}
	}

	public String getProperty(String key) {
		return this.propertiesMap.get(key);
	}

}
