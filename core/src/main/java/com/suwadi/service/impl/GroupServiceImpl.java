package com.suwadi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.GroupDAO;
import com.suwadi.domain.Group;
import com.suwadi.service.GroupService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
	private GroupDAO groupDAO;

	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	@Autowired
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public Group save(Group t) {
		return this.groupDAO.save(t);
	}

	public Group update(Group t) {
		return this.groupDAO.update(t);
	}

	public Group findById(Long id) {
		return this.groupDAO.findById(id);
	}

	public Group delete(Group t) {
		return this.groupDAO.delete(t);
	}

	public List<Group> findAll() {
		return this.groupDAO.findAll();
	}

	public List<Group> findAll(Pager pager) {
		return this.groupDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.groupDAO.paginate(pager);
	}

	public PagedResultSet paginate(Pager pager, String[] joins) {
		return this.groupDAO.paginate(pager, joins);
	}

	public Integer getAllCount() {
		return this.groupDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet findAllBySocietyId(Long societyId, Pager pager) {
		return this.groupDAO.findAllBySocietyId(societyId, pager);
	}

	public List<com.suwadi.web.json.response.Group> getAllGroupsJSONBySocietyId(
			Long societyId) {
		List<com.suwadi.web.json.response.Group> groupsJSON = new ArrayList<com.suwadi.web.json.response.Group>();
		List<Group> groups = this.findAllBySocietyId(societyId);
		for (Group group : groups) {
			com.suwadi.web.json.response.Group g = new com.suwadi.web.json.response.Group();
			g.setId(group.getId());
			g.setName(group.getName());

			groupsJSON.add(g);
		}
		return groupsJSON;
	}

	public List<Group> findAllBySocietyId(Long societyId) {
		return this.groupDAO.findAllBySocietyId(societyId);
	}

	public Group findById(Long id, String... include) {
		return this.groupDAO.findById(id, include);
	}

}
