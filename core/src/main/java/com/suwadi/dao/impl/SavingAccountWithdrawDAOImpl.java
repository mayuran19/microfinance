package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountWithdrawDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Employee;
import com.suwadi.domain.SavingAccount;
import com.suwadi.domain.SavingAccountWithdraw;
import com.suwadi.service.EmployeeService;
import com.suwadi.service.SavingAccountService;
import com.suwadi.service.SavingAccountWithdrawTypeService;
import com.suwadi.web.model.microfinance.SavingAccountWithdrawForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("savingAccountWithdrawDAO")
public class SavingAccountWithdrawDAOImpl extends
		GenericHibernateDAOSupport<SavingAccountWithdraw> implements
		SavingAccountWithdrawDAO {
	private EmployeeService employeeService;
	private SavingAccountService savingAccountService;
	private SavingAccountWithdrawTypeService savingAccountWithdrawTypeService;

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
	public void setSavingAccountWithdrawTypeService(
			SavingAccountWithdrawTypeService savingAccountWithdrawTypeService) {
		this.savingAccountWithdrawTypeService = savingAccountWithdrawTypeService;
	}

	public SavingAccountWithdrawDAOImpl() {
		super(SavingAccountWithdraw.class);
	}

	public PagedResultSet savingAccountWithdrawPaginateBySavingAccountId(
			Long savingAccountId, Pager pager) {
		String hqlQuery = String
				.format("select savingAccountWithdraw from %s savingAccountWithdraw left join fetch savingAccountWithdraw.savingAccountWithdrawType where savingAccountWithdraw.savingAccount.id = :savingAccountId",
						SavingAccountWithdraw.class.getName());
		String hqlCountQuery = String
				.format("select count(savingAccountWithdraw) from %s savingAccountWithdraw left join savingAccountWithdraw.savingAccountWithdrawType where savingAccountWithdraw.savingAccount.id = :savingAccountId",
						SavingAccountWithdraw.class.getName());
		Query query = this.getSession().createQuery(hqlQuery);
		query.setLong("savingAccountId", savingAccountId);
		query.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List<SavingAccountWithdraw> savingAccountWithdraws = query.list();
		Query countQuery = this.getSession().createQuery(hqlCountQuery)
				.setLong("savingAccountId", savingAccountId);
		Long cnt = (Long) countQuery.uniqueResult();
		int count = cnt == null ? 0 : cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(savingAccountWithdraws);
		pr.setRowCount(count);
		return pr;
	}

	public SavingAccountWithdraw createSavingAccountWithdraw(
			SavingAccountWithdrawForm savingAccountWithdrawForm) {
		SavingAccountWithdraw savingAccountWithdraw = new SavingAccountWithdraw();
		savingAccountWithdraw.setAmount(savingAccountWithdrawForm.getAmount());
		Employee processedBy = this.employeeService
				.findById(savingAccountWithdrawForm.getProcessedById());
		savingAccountWithdraw.setProcessedBy(processedBy);
		savingAccountWithdraw
				.setRemarks(savingAccountWithdrawForm.getRemarks());
		SavingAccount savingAccount = this.savingAccountService
				.findById(savingAccountWithdrawForm.getSavingAccountId());
		savingAccountWithdraw.setSavingAccount(savingAccount);
		savingAccountWithdraw.setWithdrawDate(savingAccountWithdrawForm
				.getWithdrawDate());
		savingAccountWithdraw
				.setSavingAccountWithdrawType(savingAccountWithdrawTypeService
						.findById(savingAccountWithdrawForm
								.getSavingAccountWithdrawTypeId()));
		this.save(savingAccountWithdraw);

		return savingAccountWithdraw;
	}

	public SavingAccountWithdraw updateSavingAccountWithdraw(
			SavingAccountWithdrawForm savingAccountWithdrawForm) {
		SavingAccountWithdraw savingAccountWithdraw = this
				.findById(savingAccountWithdrawForm.getId());
		savingAccountWithdraw.setAmount(savingAccountWithdrawForm.getAmount());
		Employee processedBy = this.employeeService
				.findById(savingAccountWithdrawForm.getProcessedById());
		savingAccountWithdraw.setProcessedBy(processedBy);
		savingAccountWithdraw
				.setRemarks(savingAccountWithdrawForm.getRemarks());
		SavingAccount savingAccount = this.savingAccountService
				.findById(savingAccountWithdrawForm.getSavingAccountId());
		savingAccountWithdraw.setSavingAccount(savingAccount);
		savingAccountWithdraw.setWithdrawDate(savingAccountWithdrawForm
				.getWithdrawDate());
		savingAccountWithdraw
				.setSavingAccountWithdrawType(savingAccountWithdrawTypeService
						.findById(savingAccountWithdrawForm
								.getSavingAccountWithdrawTypeId()));
		this.update(savingAccountWithdraw);

		return savingAccountWithdraw;
	}

	public SavingAccountWithdrawForm getSavingAccountWithdrawFormById(
			Long savingAccountWithdrawId) {
		SavingAccountWithdraw savingAccountWithdraw = this.findById(
				savingAccountWithdrawId, "savingAccount", "processedBy",
				"savingAccountWithdrawType");
		SavingAccountWithdrawForm savingAccountWithdrawForm = new SavingAccountWithdrawForm();
		savingAccountWithdrawForm.setAmount(savingAccountWithdraw.getAmount());
		savingAccountWithdrawForm.setId(savingAccountWithdraw.getId());
		savingAccountWithdrawForm.setProcessedById(savingAccountWithdraw
				.getProcessedBy().getId());
		savingAccountWithdrawForm
				.setRemarks(savingAccountWithdraw.getRemarks());
		savingAccountWithdrawForm.setSavingAccountId(savingAccountWithdraw
				.getSavingAccount().getId());
		savingAccountWithdrawForm.setWithdrawDate(savingAccountWithdraw
				.getWithdrawDate());
		savingAccountWithdrawForm
				.setSavingAccountWithdrawTypeId(savingAccountWithdraw
						.getSavingAccountWithdrawType() != null ? savingAccountWithdraw
						.getSavingAccountWithdrawType().getId() : null);

		return savingAccountWithdrawForm;
	}
}
