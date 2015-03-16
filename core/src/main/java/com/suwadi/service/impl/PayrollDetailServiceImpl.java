package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayrollDetailDAO;
import com.suwadi.domain.PayrollDetail;
import com.suwadi.service.PayrollDetailService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payrollDetailService")
public class PayrollDetailServiceImpl implements PayrollDetailService {
	private PayrollDetailDAO payrollDetailDAO;

	@Autowired
	public void setPayrollDetailDAO(PayrollDetailDAO payrollDetailDAO) {
		this.payrollDetailDAO = payrollDetailDAO;
	}

	public PayrollDetail save(PayrollDetail t) {
		return this.payrollDetailDAO.save(t);
	}

	public PayrollDetail update(PayrollDetail t) {
		return this.payrollDetailDAO.update(t);
	}

	public PayrollDetail findById(Long id) {
		return this.payrollDetailDAO.findById(id);
	}

	public PayrollDetail findById(Long id, String... include) {
		return this.payrollDetailDAO.findById(id, include);
	}

	public PayrollDetail delete(PayrollDetail t) {
		return this.payrollDetailDAO.delete(t);
	}

	public List<PayrollDetail> findAll() {
		return this.payrollDetailDAO.findAll();
	}

	public List<PayrollDetail> findAll(Pager pager) {
		return this.payrollDetailDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.payrollDetailDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.payrollDetailDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.payrollDetailDAO.isUnique(id, fieldName, fieldValue);
	}

}
