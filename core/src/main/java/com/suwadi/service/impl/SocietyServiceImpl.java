package com.suwadi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SocietyDAO;
import com.suwadi.domain.Society;
import com.suwadi.service.SocietyService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("societyService")
public class SocietyServiceImpl implements SocietyService {
	private SocietyDAO societyDAO;

	public SocietyDAO getSocietyDAO() {
		return societyDAO;
	}

	@Autowired
	public void setSocietyDAO(SocietyDAO societyDAO) {
		this.societyDAO = societyDAO;
	}

	public Society save(Society t) {
		return this.societyDAO.save(t);
	}

	public Society update(Society t) {
		return this.societyDAO.update(t);
	}

	public Society findById(Long id) {
		return this.societyDAO.findById(id);
	}

	public Society delete(Society t) {
		return this.societyDAO.delete(t);
	}

	public List<Society> findAll() {
		return this.societyDAO.findAll();
	}

	public List<Society> findAll(Pager pager) {
		return this.societyDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.societyDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.societyDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.societyDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet getSocietiesByGNDivisionId(Long gnDivisionId,
			Pager pager) {
		return this.societyDAO.getSocietiesByGNDivisionId(gnDivisionId, pager);
	}

	public PagedResultSet getAllSocieties(Pager pager) {
		return this.societyDAO.getAllSocieties(pager);
	}

	public List<com.suwadi.web.json.response.Society> getAllSocietiesJSONByGNDivisionId(
			Long gnDivisionId) {
		List<Society> societies = this.societyDAO
				.getSocietiesByGNDivisionId(gnDivisionId);
		List<com.suwadi.web.json.response.Society> societiesJSON = new ArrayList<com.suwadi.web.json.response.Society>();
		for (Society society : societies) {
			com.suwadi.web.json.response.Society societyJSON = new com.suwadi.web.json.response.Society();
			societyJSON.setId(society.getId());
			societyJSON.setName(society.getName());
			societiesJSON.add(societyJSON);
		}

		return societiesJSON;
	}

	public List<Society> findAllByGNDivisionId(Long gnDivisionId) {
		return this.societyDAO.getSocietiesByGNDivisionId(gnDivisionId);
	}

	public Society findById(Long id, String... include) {
		return this.societyDAO.findById(id, include);
	}

}
