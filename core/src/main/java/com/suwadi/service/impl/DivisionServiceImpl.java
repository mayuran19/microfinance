package com.suwadi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.DivisionDAO;
import com.suwadi.domain.Division;
import com.suwadi.service.DivisionService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("divisionService")
public class DivisionServiceImpl implements DivisionService {
	private DivisionDAO divisionDAO;

	public DivisionDAO getDivisionDAO() {
		return divisionDAO;
	}

	@Autowired
	public void setDivisionDAO(DivisionDAO divisionDAO) {
		this.divisionDAO = divisionDAO;
	}

	public Division save(Division t) {
		return this.getDivisionDAO().save(t);
	}

	public Division update(Division t) {
		return this.getDivisionDAO().update(t);
	}

	public Division findById(Long id) {
		return this.getDivisionDAO().findById(id);
	}

	public Division delete(Division t) {
		return this.getDivisionDAO().delete(t);
	}

	public List<Division> findAll() {
		return this.getDivisionDAO().findAll();
	}

	public List<Division> findAll(Pager pager) {
		return this.getDivisionDAO().findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.getDivisionDAO().paginate(pager);
	}

	public Integer getAllCount() {
		return this.getDivisionDAO().getAllCount();
	}

	public List<Division> findAllByDistrictId(Long districtId) {
		return this.getDivisionDAO().findAllByDistrictId(districtId);
	}

	public List<com.suwadi.web.json.response.Division> findAllDivisionsJSONByDistrictId(
			Long districtId) {
		List<Division> divisionList = this.findAllByDistrictId(districtId);
		List<com.suwadi.web.json.response.Division> divisionListJSON = new ArrayList<com.suwadi.web.json.response.Division>();
		for (Division division : divisionList) {
			com.suwadi.web.json.response.Division divisionJSON = new com.suwadi.web.json.response.Division();
			divisionJSON.setId(division.getId());
			divisionJSON.setName(division.getName());

			divisionListJSON.add(divisionJSON);
		}
		return divisionListJSON;
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.divisionDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet findAllByDistrictId(Long districtId, Pager pager) {
		return this.divisionDAO.findAllByDistrictId(districtId, pager);
	}

	public Division findById(Long id, String... include) {
		return this.divisionDAO.findById(id, include);
	}

}
