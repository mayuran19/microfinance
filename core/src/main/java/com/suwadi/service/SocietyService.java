package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.Society;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface SocietyService extends GenericService<Society> {
	public PagedResultSet getSocietiesByGNDivisionId(Long gnDivisionId,
			Pager pager);

	public PagedResultSet getAllSocieties(Pager pager);

	public List<com.suwadi.web.json.response.Society> getAllSocietiesJSONByGNDivisionId(Long gnDivisionId);
	
	public List<Society> findAllByGNDivisionId(Long gnDivisionId);
}
