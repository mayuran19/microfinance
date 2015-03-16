package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.Division;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface DivisionDAO extends GenericDAO<Division> {
	public List<Division> findAllByDistrictId(Long districtId);

	public PagedResultSet findAllByDistrictId(Long districtId, Pager pager);
}
