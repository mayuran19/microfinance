package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.PayType;

public interface PayTypeService extends GenericService<PayType> {
	public List<PayType> findAllFixedPayTypes();
}
