package com.suwadi.dao;

import com.suwadi.domain.Beneficiary;
import com.suwadi.web.model.BeneficiarySearch;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface BeneficiaryDAO extends GenericDAO<Beneficiary> {
	public PagedResultSet findAllBeneficiaries(Pager pager);

	public PagedResultSet findByBenificiarySearchCriteria(
			BeneficiarySearch beneficiarySearch, Pager pager);

	public PagedResultSet findAllByGroupId(Long groupId, Pager pager);
}
