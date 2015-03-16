package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.FamilyRelationshipDAO;
import com.suwadi.domain.FamilyRelationship;
import com.suwadi.service.FamilyRelationshipService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("familyRelationshipService")
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
	private FamilyRelationshipDAO familyRelationshipDAO;

	public FamilyRelationshipDAO getFamilyRelationshipDAO() {
		return familyRelationshipDAO;
	}

	@Autowired
	public void setFamilyRelationshipDAO(
			FamilyRelationshipDAO familyRelationshipDAO) {
		this.familyRelationshipDAO = familyRelationshipDAO;
	}

	public FamilyRelationship save(FamilyRelationship t) {
		return this.familyRelationshipDAO.save(t);
	}

	public FamilyRelationship update(FamilyRelationship t) {
		return this.familyRelationshipDAO.update(t);
	}

	public FamilyRelationship findById(Long id) {
		return this.familyRelationshipDAO.findById(id);
	}

	public FamilyRelationship delete(FamilyRelationship t) {
		return this.familyRelationshipDAO.delete(t);
	}

	public List<FamilyRelationship> findAll() {
		return this.familyRelationshipDAO.findAll();
	}

	public List<FamilyRelationship> findAll(Pager pager) {
		return this.familyRelationshipDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.familyRelationshipDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.familyRelationshipDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.familyRelationshipDAO.isUnique(id, fieldName, fieldValue);
	}

	public FamilyRelationship findById(Long id, String... include) {
		return this.findById(id, include);
	}

}
