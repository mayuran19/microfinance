package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.Division;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface DivisionService extends GenericService<Division> {
	public List<Division> findAllByDistrictId(Long districtId);

	public List<com.suwadi.web.json.response.Division> findAllDivisionsJSONByDistrictId(
			Long districtId);
	
	public PagedResultSet findAllByDistrictId(Long districtId, Pager pager);
}
