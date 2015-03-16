package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.GNDivision;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface GNDivisionService extends GenericService<GNDivision> {
	public PagedResultSet findAllByDivisionId(Long divisionId, Pager pager);

	public List<com.suwadi.web.json.response.GNDivision> getAllGNDivisionsJSONByDivisionId(
			Long divisionId);

	public List<GNDivision> findAllByDivisionId(Long divisionId);
}
