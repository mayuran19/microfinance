package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.ConfigurationDAO;
import com.suwadi.domain.Configuration;
import com.suwadi.service.ConfigurationService;
import com.suwadi.web.model.common.ConfigurationForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("configurationService")
public class ConfigurationServiceImpl implements ConfigurationService {
	private ConfigurationDAO configurationDAO;

	@Autowired
	public void setConfigurationDAO(ConfigurationDAO configurationDAO) {
		this.configurationDAO = configurationDAO;
	}

	public Configuration save(Configuration t) {
		return this.configurationDAO.save(t);
	}

	public Configuration update(Configuration t) {
		return this.configurationDAO.update(t);
	}

	public Configuration findById(Long id) {
		return this.configurationDAO.findById(id);
	}

	public Configuration findById(Long id, String... include) {
		return this.configurationDAO.findById(id, include);
	}

	public Configuration delete(Configuration t) {
		return this.configurationDAO.delete(t);
	}

	public List<Configuration> findAll() {
		return this.configurationDAO.findAll();
	}

	public List<Configuration> findAll(Pager pager) {
		return this.configurationDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.configurationDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.configurationDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.configurationDAO.isUnique(id, fieldName, fieldValue);
	}

	public ConfigurationForm findByConfigurationId(Long configurationId) {
		ConfigurationForm configurationForm = new ConfigurationForm();
		Configuration configuration = this.configurationDAO
				.findById(configurationId);
		configurationForm.setId(configuration.getId());
		configurationForm.setKey(configuration.getConfKey());
		configurationForm.setValue(configuration.getConfValue());
		return configurationForm;
	}

	public ConfigurationForm save(ConfigurationForm configurationForm) {
		Configuration configuration = new Configuration();
		configuration.setConfKey(configurationForm.getKey());
		configuration.setConfValue(configurationForm.getValue());
		this.save(configuration);
		return configurationForm;
	}

	public ConfigurationForm update(ConfigurationForm configurationForm) {
		Configuration configuration = this.findById(configurationForm.getId());
		configuration.setConfKey(configurationForm.getKey());
		configuration.setConfValue(configurationForm.getValue());
		this.update(configuration);
		return configurationForm;
	}

	public String findValueByKey(String key) {
		return this.configurationDAO.findValueByKey(key);
	}

}
