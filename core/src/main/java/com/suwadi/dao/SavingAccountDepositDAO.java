package com.suwadi.dao;

import com.suwadi.domain.SavingAccountDeposit;
import com.suwadi.web.model.microfinance.SavingAccountDepositForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface SavingAccountDepositDAO extends
		GenericDAO<SavingAccountDeposit> {
	public PagedResultSet savingAccountDepoistPaginateBySavingAccountId(
			Long savingAccountId, Pager pager);

	public SavingAccountDeposit createSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm);

	public SavingAccountDeposit updateSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm);

	public SavingAccountDepositForm getSavingAccountDepositFormById(
			Long savingAccountDepositId);
}
