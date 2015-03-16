package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.Society;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface SocietyDAO extends GenericDAO<Society> {
	public PagedResultSet getSocietiesByGNDivisionId(Long gnDivisionId,
			Pager pager);

	public PagedResultSet getAllSocieties(Pager pager);

	public List<Society> getSocietiesByGNDivisionId(Long gnDivisionId);

}
