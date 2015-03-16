package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.District;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface DistrictService extends GenericService<District> {
	public List<com.suwadi.web.json.response.District> getAllJSONDistricts();
	public List<com.suwadi.web.json.response.District> getAllActiveDistricts();
	public List<District> findAllActiveDistrict();
	public PagedResultSet findAllActiveDistrict(Pager pager);
}
