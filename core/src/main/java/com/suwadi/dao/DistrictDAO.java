package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.District;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface DistrictDAO extends GenericDAO<District> {
	public List<District> findAllActiveDistrict();
	public PagedResultSet findAllActiveDistrict(Pager pager);
}
