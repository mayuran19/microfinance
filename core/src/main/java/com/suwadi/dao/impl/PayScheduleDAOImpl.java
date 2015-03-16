package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayScheduleDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.PaySchedule;

@Repository("payScheduleDAO")
public class PayScheduleDAOImpl extends GenericHibernateDAOSupport<PaySchedule>
		implements PayScheduleDAO {
	public PayScheduleDAOImpl() {
		super(PaySchedule.class);
	}

}
