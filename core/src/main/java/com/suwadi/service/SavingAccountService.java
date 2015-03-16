package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.SavingAccount;
import com.suwadi.web.model.microfinance.SavingAccountForm;
import com.suwadi.web.model.microfinance.SavingAccountListDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface SavingAccountService extends GenericService<SavingAccount> {
	public PagedResultSet paginateSavingAccount(Pager pager);

	public List<SavingAccountListDisplay> beneficiarySavingAccounts(
			Long beneficiaryId);

	public SavingAccount createSavingAccount(SavingAccountForm savingAccountForm);

	public SavingAccount updateSavingAccount(SavingAccountForm savingAccountForm);

	public SavingAccountForm getServiceAccountFormById(Long serviceAccountId);

	public List<SavingAccountListDisplay> societySavingAccounts(Long societyId);
}
