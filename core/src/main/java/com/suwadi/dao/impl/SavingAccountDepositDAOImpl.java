package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountDepositDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Employee;
import com.suwadi.domain.SavingAccount;
import com.suwadi.domain.SavingAccountDeposit;
import com.suwadi.service.EmployeeService;
import com.suwadi.service.SavingAccountDepositTypeService;
import com.suwadi.service.SavingAccountService;
import com.suwadi.web.model.microfinance.SavingAccountDepositForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("savingAccountDepositDAO")
public class SavingAccountDepositDAOImpl extends
		GenericHibernateDAOSupport<SavingAccountDeposit> implements
		SavingAccountDepositDAO {
	private EmployeeService employeeService;
	private SavingAccountService savingAccountService;
	private SavingAccountDepositTypeService savingAccountDepositTypeService;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setSavingAccountService(
			SavingAccountService savingAccountService) {
		this.savingAccountService = savingAccountService;
	}

	@Autowired
	public void setSavingAccountDepositTypeService(
			SavingAccountDepositTypeService savingAccountDepositTypeService) {
		this.savingAccountDepositTypeService = savingAccountDepositTypeService;
	}

	public SavingAccountDepositDAOImpl() {
		super(SavingAccountDeposit.class);
	}

	public PagedResultSet savingAccountDepoistPaginateBySavingAccountId(
			Long savingAccountId, Pager pager) {
		String hqlQuery = String
				.format("select savingAccountDeposit from %s savingAccountDeposit left join fetch savingAccountDeposit.savingAccountDepositType where savingAccountDeposit.savingAccount.id = :savingAccountId",
						SavingAccountDeposit.class.getName());
		String hqlCountQuery = String
				.format("select count(savingAccountDeposit) from %s savingAccountDeposit left join savingAccountDeposit.savingAccountDepositType where savingAccountDeposit.savingAccount.id = :savingAccountId",
						SavingAccountDeposit.class.getName());
		Query query = this.getSession().createQuery(hqlQuery);
		query.setLong("savingAccountId", savingAccountId);
		query.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List<SavingAccountDeposit> savingAccountDeposits = query.list();
		Query countQuery = this.getSession().createQuery(hqlCountQuery)
				.setLong("savingAccountId", savingAccountId);
		Long cnt = (Long) countQuery.uniqueResult();
		int count = cnt == null ? 0 : cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(savingAccountDeposits);
		pr.setRowCount(count);
		return pr;
	}

	public SavingAccountDeposit createSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm) {
		SavingAccountDeposit savingAccountDeposit = new SavingAccountDeposit();
		savingAccountDeposit.setAmount(savingAccountDepositForm.getAmount());
		Employee employee = this.employeeService
				.findById(savingAccountDepositForm.getCollectedById());
		savingAccountDeposit.setCollectedBy(employee);
		savingAccountDeposit.setDepositDate(savingAccountDepositForm
				.getDepositDate());
		savingAccountDeposit.setRemarks(savingAccountDepositForm.getRemarks());
		SavingAccount savingAccount = this.savingAccountService
				.findById(savingAccountDepositForm.getSavingAccountId());
		savingAccountDeposit.setSavingAccount(savingAccount);
		savingAccountDeposit
				.setSavingAccountDepositType(savingAccountDepositTypeService
						.findById(savingAccountDepositForm
								.getSavingAccountDepositTypeId()));
		this.save(savingAccountDeposit);

		return savingAccountDeposit;
	}

	public SavingAccountDeposit updateSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm) {
		SavingAccountDeposit savingAccountDeposit = this
				.findById(savingAccountDepositForm.getId());
		savingAccountDeposit.setAmount(savingAccountDepositForm.getAmount());
		Employee employee = this.employeeService
				.findById(savingAccountDepositForm.getCollectedById());
		savingAccountDeposit.setCollectedBy(employee);
		savingAccountDeposit.setDepositDate(savingAccountDepositForm
				.getDepositDate());
		savingAccountDeposit.setRemarks(savingAccountDepositForm.getRemarks());
		SavingAccount savingAccount = this.savingAccountService
				.findById(savingAccountDepositForm.getSavingAccountId());
		savingAccountDeposit.setSavingAccount(savingAccount);
		savingAccountDeposit
				.setSavingAccountDepositType(savingAccountDepositTypeService
						.findById(savingAccountDepositForm
								.getSavingAccountDepositTypeId()));
		this.update(savingAccountDeposit);

		return savingAccountDeposit;
	}

	public SavingAccountDepositForm getSavingAccountDepositFormById(
			Long savingAccountDepositId) {
		SavingAccountDeposit savingAccountDeposit = this.findById(
				savingAccountDepositId, "savingAccount", "collectedBy",
				"savingAccountDepositType");
		SavingAccountDepositForm savingAccountDepositForm = new SavingAccountDepositForm();
		savingAccountDepositForm.setAmount(savingAccountDeposit.getAmount());
		savingAccountDepositForm.setCollectedById(savingAccountDeposit
				.getCollectedBy().getId());
		savingAccountDepositForm.setDepositDate(savingAccountDeposit
				.getDepositDate());
		savingAccountDepositForm.setId(savingAccountDeposit.getId());
		savingAccountDepositForm.setRemarks(savingAccountDeposit.getRemarks());
		savingAccountDepositForm.setSavingAccountId(savingAccountDeposit
				.getSavingAccount() != null ? savingAccountDeposit
				.getSavingAccount().getId() : null);
		savingAccountDepositForm
				.setSavingAccountDepositTypeId(savingAccountDeposit
						.getSavingAccountDepositType() != null ? savingAccountDeposit
						.getSavingAccountDepositType().getId() : null);
		return savingAccountDepositForm;
	}

}
