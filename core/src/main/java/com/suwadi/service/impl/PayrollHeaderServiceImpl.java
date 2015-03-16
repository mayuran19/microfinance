package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayrollHeaderDAO;
import com.suwadi.domain.PayRollHeader;
import com.suwadi.service.PayrollHeaderService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payrollHeaderService")
public class PayrollHeaderServiceImpl implements PayrollHeaderService {
	private PayrollHeaderDAO payrollHeaderDAO;

	@Autowired
	public void setPayrollHeaderDAO(PayrollHeaderDAO payrollHeaderDAO) {
		this.payrollHeaderDAO = payrollHeaderDAO;
	}

	public PayRollHeader save(PayRollHeader t) {
		return this.payrollHeaderDAO.save(t);
	}

	public PayRollHeader update(PayRollHeader t) {
		return null;
	}

	public PayRollHeader findById(Long id) {
		return null;
	}

	public PayRollHeader findById(Long id, String... include) {
		return null;
	}

	public PayRollHeader delete(PayRollHeader t) {
		return null;
	}

	public List<PayRollHeader> findAll() {
		return null;
	}

	public List<PayRollHeader> findAll(Pager pager) {
		return null;
	}

	public PagedResultSet paginate(Pager pager) {
		return null;
	}

	public Integer getAllCount() {
		return null;
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return null;
	}

}
