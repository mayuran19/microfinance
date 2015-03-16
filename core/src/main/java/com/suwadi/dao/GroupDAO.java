package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.Group;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface GroupDAO extends GenericDAO<Group> {
	public PagedResultSet findAllBySocietyId(Long societyId, Pager pager);

	public List<Group> findAllBySocietyId(Long societyId);
}
