package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.PayTax;

public interface PayTaxDAO extends GenericDAO<PayTax> {
	public List<PayTax> findAllFixedPayTaxes();
}
