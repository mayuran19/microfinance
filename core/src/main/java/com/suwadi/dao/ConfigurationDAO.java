package com.suwadi.dao;

import com.suwadi.domain.Configuration;

public interface ConfigurationDAO extends GenericDAO<Configuration> {
	public String findValueByKey(String key);
}
