package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.CustomerDAO;
import com.suwadi.domain.Customer;
import com.suwadi.service.CustomerService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO customerDAO;

	@Autowired
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public Customer save(Customer t) {
		return this.customerDAO.save(t);
	}

	public Customer update(Customer t) {
		return this.customerDAO.update(t);
	}

	public Customer findById(Long id) {
		return this.customerDAO.findById(id);
	}

	public Customer findById(Long id, String... include) {
		return this.customerDAO.findById(id, include);
	}

	public Customer delete(Customer t) {
		return this.customerDAO.delete(t);
	}

	public List<Customer> findAll() {
		return this.customerDAO.findAll();
	}

	public List<Customer> findAll(Pager pager) {
		return this.customerDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.customerDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.customerDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.customerDAO.isUnique(id, fieldName, fieldValue);
	}

}
