package com.suwadi.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.ConfigurationDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Configuration;

@Repository("configurationDAO")
public class ConfigurationDAOImpl extends
		GenericHibernateDAOSupport<Configuration> implements ConfigurationDAO {
	public ConfigurationDAOImpl() {
		super(Configuration.class);
	}

	public String findValueByKey(String key) {
		String hqlQuery = String
				.format("select obj.confValue from %s obj where obj.confKey = :confKey",
						Configuration.class.getName());
		Query query = this.getSession().createQuery(hqlQuery)
				.setString("confKey", key);
		return (String) query.uniqueResult();
	}
}
