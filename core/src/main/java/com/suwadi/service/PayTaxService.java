package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.PayTax;

public interface PayTaxService extends GenericService<PayTax> {
	public List<PayTax> findAllFixedPayTaxes();
}
