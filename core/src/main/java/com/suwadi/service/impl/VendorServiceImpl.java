package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.VendorDAO;
import com.suwadi.domain.Vendor;
import com.suwadi.service.VendorService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("vendorService")
public class VendorServiceImpl implements VendorService {
	private VendorDAO vendorDAO;

	@Autowired
	public void setVendorDAO(VendorDAO vendorDAO) {
		this.vendorDAO = vendorDAO;
	}

	public Vendor save(Vendor t) {
		return this.vendorDAO.save(t);
	}

	public Vendor update(Vendor t) {
		return this.vendorDAO.update(t);
	}

	public Vendor findById(Long id) {
		return this.vendorDAO.findById(id);
	}

	public Vendor delete(Vendor t) {
		return this.vendorDAO.delete(t);
	}

	public List<Vendor> findAll() {
		return this.vendorDAO.findAll();
	}

	public List<Vendor> findAll(Pager pager) {
		return this.vendorDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.vendorDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.vendorDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.vendorDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet findAllActiveVendors(Pager pager) {
		return this.vendorDAO.findAllActiveVendors(pager);
	}

	public Vendor findById(Long id, String... include) {
		return this.vendorDAO.findById(id, include);
	}

}
