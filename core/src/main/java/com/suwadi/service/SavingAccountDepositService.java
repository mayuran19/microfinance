package com.suwadi.service;

import com.suwadi.domain.SavingAccountDeposit;
import com.suwadi.web.model.microfinance.SavingAccountDepositForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface SavingAccountDepositService extends
		GenericService<SavingAccountDeposit> {
	public PagedResultSet savingAccountDepoistPaginateBySavingAccountId(
			Long savingAccountId, Pager pager);

	public SavingAccountDeposit createSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm);

	public SavingAccountDeposit updateSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm);

	public SavingAccountDepositForm getSavingAccountDepositFormById(
			Long savingAccountDepositId);
}
