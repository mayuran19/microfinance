package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.SavingAccount;
import com.suwadi.web.model.microfinance.SavingAccountListDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface SavingAccountDAO extends GenericDAO<SavingAccount> {
	public PagedResultSet paginateSavingAccount(Pager pager);

	public List<SavingAccountListDisplay> beneficiarySavingAccounts(
			Long beneficiaryId);

	public List<SavingAccountListDisplay> societySavingAccounts(Long societyId);
}
