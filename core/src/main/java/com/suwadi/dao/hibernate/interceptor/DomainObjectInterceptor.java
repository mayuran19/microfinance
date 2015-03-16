package com.suwadi.dao.hibernate.interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import com.suwadi.domain.DomainObject;
import com.suwadi.domain.Status;

@Component("domainObjectInterceptor")
public class DomainObjectInterceptor extends EmptyInterceptor {

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof DomainObject) {
			Date now = new Date();
			this.setValue(currentState, propertyNames, "updatedAt", now);
			return false;
		} else {
			return false;
		}
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof DomainObject) {
			Date now = new Date();

			this.setValue(state, propertyNames, "createdAt", now);
			this.setValue(state, propertyNames, "updatedAt", now);
			this.setValue(state, propertyNames, "status", Status.ACTIVE);

			return true;
		} else {
			return false;
		}
	}

	private void setValue(Object currentState[], String[] propertyNames,
			String propertyToSet, Object value) {
		int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		if (index >= 0) {
			currentState[index] = value;
		}
	}
}
