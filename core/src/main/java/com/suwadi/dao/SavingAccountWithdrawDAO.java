package com.suwadi.dao;

import com.suwadi.domain.SavingAccountWithdraw;
import com.suwadi.web.model.microfinance.SavingAccountWithdrawForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface SavingAccountWithdrawDAO extends
		GenericDAO<SavingAccountWithdraw> {
	public PagedResultSet savingAccountWithdrawPaginateBySavingAccountId(
			Long savingAccountId, Pager pager);

	public SavingAccountWithdraw createSavingAccountWithdraw(
			SavingAccountWithdrawForm savingAccountWithdrawForm);

	public SavingAccountWithdraw updateSavingAccountWithdraw(
			SavingAccountWithdrawForm savingAccountWithdrawForm);

	public SavingAccountWithdrawForm getSavingAccountWithdrawFormById(
			Long savingAccountWithdrawId);
}
