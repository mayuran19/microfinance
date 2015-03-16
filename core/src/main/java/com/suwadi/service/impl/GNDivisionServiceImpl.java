package com.suwadi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.GNDivisionDAO;
import com.suwadi.domain.Division;
import com.suwadi.domain.GNDivision;
import com.suwadi.service.GNDivisionService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("gnDivisionService")
public class GNDivisionServiceImpl implements GNDivisionService {
	private GNDivisionDAO gnDivisionDAO;

	public GNDivisionDAO getGnDivisionDAO() {
		return gnDivisionDAO;
	}

	@Autowired
	public void setGnDivisionDAO(GNDivisionDAO gnDivisionDAO) {
		this.gnDivisionDAO = gnDivisionDAO;
	}

	public GNDivision save(GNDivision t) {
		return this.gnDivisionDAO.save(t);
	}

	public GNDivision update(GNDivision t) {
		return this.gnDivisionDAO.update(t);
	}

	public GNDivision findById(Long id) {
		return this.gnDivisionDAO.findById(id);
	}

	public GNDivision delete(GNDivision t) {
		return this.gnDivisionDAO.delete(t);
	}

	public List<GNDivision> findAll() {
		return this.gnDivisionDAO.findAll();
	}

	public List<GNDivision> findAll(Pager pager) {
		return this.gnDivisionDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.gnDivisionDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.gnDivisionDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.gnDivisionDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet findAllByDivisionId(Long divisionId, Pager pager) {
		return this.gnDivisionDAO.findAllByDivisionId(divisionId, pager);
	}

	public List<com.suwadi.web.json.response.GNDivision> getAllGNDivisionsJSONByDivisionId(
			Long divisionId) {
		List<GNDivision> gnDivisionList = this.gnDivisionDAO
				.findAllByDivisionId(divisionId);
		List<com.suwadi.web.json.response.GNDivision> gnDivisionListJSON = new ArrayList<com.suwadi.web.json.response.GNDivision>();
		for (GNDivision gnDivision : gnDivisionList) {
			com.suwadi.web.json.response.GNDivision gnDivisionJSON = new com.suwadi.web.json.response.GNDivision();
			gnDivisionJSON.setId(gnDivision.getId());
			gnDivisionJSON.setName(gnDivision.getName());

			gnDivisionListJSON.add(gnDivisionJSON);
		}
		return gnDivisionListJSON;
	}

	public List<GNDivision> findAllByDivisionId(Long divisionId) {
		return this.gnDivisionDAO.findAllByDivisionId(divisionId);
	}

	public GNDivision findById(Long id, String... include) {
		return this.gnDivisionDAO.findById(id, include);
	}

}
