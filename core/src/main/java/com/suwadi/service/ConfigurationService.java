package com.suwadi.service;

import com.suwadi.domain.Configuration;
import com.suwadi.web.model.common.ConfigurationForm;

public interface ConfigurationService extends GenericService<Configuration> {
	public ConfigurationForm findByConfigurationId(Long configurationId);

	public ConfigurationForm save(ConfigurationForm configurationForm);

	public ConfigurationForm update(ConfigurationForm configurationForm);

	public String findValueByKey(String key);
}
