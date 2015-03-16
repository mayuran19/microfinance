package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.GNDivision;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface GNDivisionDAO extends GenericDAO<GNDivision> {
	public PagedResultSet findAllByDivisionId(Long divisionId, Pager pager);

	public List<GNDivision> findAllByDivisionId(Long divisionId);
}
