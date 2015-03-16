package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.PayType;

public interface PayTypeDAO extends GenericDAO<PayType> {
	public List<PayType> findAllFixedPayTypes();
}
