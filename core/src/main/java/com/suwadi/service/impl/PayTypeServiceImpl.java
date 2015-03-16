package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayTypeDAO;
import com.suwadi.domain.PayType;
import com.suwadi.service.PayTypeService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payTypeService")
public class PayTypeServiceImpl implements PayTypeService {
	private PayTypeDAO payTypeDAO;

	@Autowired
	public void setPayTypeDAO(PayTypeDAO payTypeDAO) {
		this.payTypeDAO = payTypeDAO;
	}

	public PayType save(PayType t) {
		return this.payTypeDAO.save(t);
	}

	public PayType update(PayType t) {
		return this.payTypeDAO.update(t);
	}

	public PayType findById(Long id) {
		return this.payTypeDAO.findById(id);
	}

	public PayType findById(Long id, String... include) {
		return this.payTypeDAO.findById(id, include);
	}

	public PayType delete(PayType t) {
		return this.payTypeDAO.delete(t);
	}

	public List<PayType> findAll() {
		return this.payTypeDAO.findAll();
	}

	public List<PayType> findAll(Pager pager) {
		return this.payTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.payTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.payTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.payTypeDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<PayType> findAllFixedPayTypes() {
		return this.payTypeDAO.findAllFixedPayTypes();
	}

}
