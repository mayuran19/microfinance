package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountDAO;
import com.suwadi.domain.Beneficiary;
import com.suwadi.domain.SavingAccount;
import com.suwadi.domain.SavingAccountType;
import com.suwadi.domain.Society;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.SavingAccountService;
import com.suwadi.service.SavingAccountTypeService;
import com.suwadi.service.SocietyService;
import com.suwadi.web.model.microfinance.SavingAccountForm;
import com.suwadi.web.model.microfinance.SavingAccountListDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountService")
public class SavingAccountServiceImpl implements SavingAccountService {
	private SavingAccountDAO savingAccountDAO;
	private BeneficiaryService beneficiaryService;
	private SavingAccountTypeService savingAccountTypeService;
	private SocietyService societyService;

	@Autowired
	public void setSavingAccountDAO(SavingAccountDAO savingAccountDAO) {
		this.savingAccountDAO = savingAccountDAO;
	}

	@Autowired
	public void setBeneficiaryService(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@Autowired
	public void setSavingAccountTypeService(
			SavingAccountTypeService savingAccountTypeService) {
		this.savingAccountTypeService = savingAccountTypeService;
	}

	@Autowired
	public void setSocietyService(SocietyService societyService) {
		this.societyService = societyService;
	}

	public SavingAccount save(SavingAccount t) {
		return this.savingAccountDAO.save(t);
	}

	public SavingAccount update(SavingAccount t) {
		return this.savingAccountDAO.update(t);
	}

	public SavingAccount findById(Long id) {
		return this.savingAccountDAO.findById(id);
	}

	public SavingAccount findById(Long id, String... include) {
		return this.savingAccountDAO.findById(id, include);
	}

	public SavingAccount delete(SavingAccount t) {
		return this.savingAccountDAO.delete(t);
	}

	public List<SavingAccount> findAll() {
		return this.savingAccountDAO.findAll();
	}

	public List<SavingAccount> findAll(Pager pager) {
		return this.savingAccountDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet paginateSavingAccount(Pager pager) {
		return this.savingAccountDAO.paginateSavingAccount(pager);
	}

	public List<SavingAccountListDisplay> beneficiarySavingAccounts(
			Long beneficiaryId) {
		return this.savingAccountDAO.beneficiarySavingAccounts(beneficiaryId);
	}

	public SavingAccount createSavingAccount(SavingAccountForm savingAccountForm) {
		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setAccountHolderName(savingAccountForm
				.getAccountHolderName());
		savingAccount.setAccountNumber(savingAccountForm.getAccountNumber());
		if (savingAccountForm.getBeneficiaryId() != null) {
			Beneficiary beneficiary = this.beneficiaryService
					.findById(savingAccountForm.getBeneficiaryId());
			savingAccount.setBeneficiary(beneficiary);
		}
		if (savingAccountForm.getSocietyId() != null) {
			Society society = this.societyService.findById(savingAccountForm
					.getSocietyId());
			savingAccount.setSociety(society);
		}

		savingAccount.setFromDate(savingAccountForm.getOpenedDate());
		savingAccount.setRemarks(savingAccountForm.getRemarks());
		SavingAccountType savingAccountType = this.savingAccountTypeService
				.findById(savingAccountForm.getSavingAccountTypeId());
		savingAccount.setSavingAccountType(savingAccountType);
		savingAccount.setThroughDate(null);
		this.save(savingAccount);
		return savingAccount;
	}

	public SavingAccount updateSavingAccount(SavingAccountForm savingAccountForm) {
		SavingAccount savingAccount = this.findById(savingAccountForm.getId());
		savingAccount.setAccountHolderName(savingAccountForm
				.getAccountHolderName());
		savingAccount.setAccountNumber(savingAccountForm.getAccountNumber());
		if (savingAccountForm.getBeneficiaryId() != null) {
			Beneficiary beneficiary = this.beneficiaryService
					.findById(savingAccountForm.getBeneficiaryId());
			savingAccount.setBeneficiary(beneficiary);
		}
		if (savingAccountForm.getSocietyId() != null) {
			Society society = this.societyService.findById(savingAccountForm
					.getSocietyId());
			savingAccount.setSociety(society);
		}
		savingAccount.setFromDate(savingAccountForm.getOpenedDate());
		savingAccount.setRemarks(savingAccountForm.getRemarks());
		SavingAccountType savingAccountType = this.savingAccountTypeService
				.findById(savingAccountForm.getSavingAccountTypeId());
		savingAccount.setSavingAccountType(savingAccountType);
		savingAccount.setThroughDate(null);
		this.update(savingAccount);
		return savingAccount;
	}

	public SavingAccountForm getServiceAccountFormById(Long serviceAccountId) {
		SavingAccount savingAccount = this.findById(serviceAccountId,
				"beneficiary", "savingAccountType", "society");
		SavingAccountForm savingAccountForm = new SavingAccountForm();
		savingAccountForm.setAccountHolderName(savingAccount
				.getAccountHolderName());
		savingAccountForm.setAccountNumber(savingAccount.getAccountNumber());
		savingAccountForm.setAddress(savingAccount.getAddress());
		savingAccountForm.setBeneficiaryId(savingAccount.getBeneficiary()
				.getId());
		savingAccountForm.setId(serviceAccountId);
		savingAccountForm.setOpenedDate(savingAccount.getFromDate());
		savingAccountForm.setRemarks(savingAccount.getRemarks());
		savingAccountForm.setSavingAccountTypeId(savingAccount
				.getSavingAccountType().getId());
		return savingAccountForm;
	}

	public List<SavingAccountListDisplay> societySavingAccounts(Long societyId) {
		return this.savingAccountDAO.societySavingAccounts(societyId);
	}
}
