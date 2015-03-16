package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.DesignationDAO;
import com.suwadi.domain.Designation;
import com.suwadi.service.DesignationService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("designationService")
public class DesignationServiceImpl implements DesignationService {
	private DesignationDAO designationDAO;

	public DesignationDAO getDesignationDAO() {
		return designationDAO;
	}

	@Autowired
	public void setDesignationDAO(DesignationDAO designationDAO) {
		this.designationDAO = designationDAO;
	}

	public Designation save(Designation t) {
		return this.designationDAO.save(t);
	}

	public Designation update(Designation t) {
		return this.designationDAO.update(t);
	}

	public Designation findById(Long id) {
		return this.designationDAO.findById(id);
	}

	public Designation delete(Designation t) {
		return this.designationDAO.delete(t);
	}

	public List<Designation> findAll() {
		return this.designationDAO.findAll();
	}

	public List<Designation> findAll(Pager pager) {
		return this.designationDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.designationDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.designationDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.designationDAO.isUnique(id, fieldName, fieldValue);
	}

	public Designation findById(Long id, String... include) {
		return this.designationDAO.findById(id, include);
	}

}
