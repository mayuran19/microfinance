package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.Group;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface GroupService extends GenericService<Group> {
	public PagedResultSet findAllBySocietyId(Long societyId, Pager pager);

	public List<com.suwadi.web.json.response.Group> getAllGroupsJSONBySocietyId(
			Long societyId);

	public List<Group> findAllBySocietyId(Long societyId);

	public PagedResultSet paginate(Pager pager, String[] joins);
}
