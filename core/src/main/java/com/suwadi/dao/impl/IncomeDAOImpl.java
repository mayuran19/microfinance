package com.suwadi.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.IncomeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Income;
import com.suwadi.domain.IncomeDetail;
import com.suwadi.utils.spring.CustomPropertyPlaceholderConfigurer;
import com.suwadi.web.model.accounting.IncomeDetailForm;
import com.suwadi.web.model.accounting.IncomeForm;
import com.suwadi.web.model.accounting.IncomeListDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("IncomeDAO")
public class IncomeDAOImpl extends GenericHibernateDAOSupport<Income> implements
		IncomeDAO {
	CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer;

	@Autowired
	public void setPropertyPlaceholderConfigurer(
			CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer) {
		this.propertyPlaceholderConfigurer = propertyPlaceholderConfigurer;
	}

	public IncomeDAOImpl() {
		super(Income.class);
	}

	public PagedResultSet findAllIncomes(Pager pager) {
		List<IncomeListDisplay> lst = new ArrayList<IncomeListDisplay>();
		String sqlQuery = this.propertyPlaceholderConfigurer
				.getProperty("expense.findAllIncomes");
		String countSqlQuery = "select count(distinct acc_incomes.id) from acc_incomes";
		Query query = this.getSession().createSQLQuery(sqlQuery)
				.addScalar("id", Hibernate.LONG)
				.addScalar("customer_name", Hibernate.STRING)
				.addScalar("total", Hibernate.BIG_DECIMAL)
				.addScalar("invoice_no", Hibernate.STRING)
				.addScalar("date", Hibernate.DATE)
				.addScalar("due_date", Hibernate.DATE)
				.addScalar("cheque_no", Hibernate.STRING);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List results = query.list();
		for (Object obj : results) {
			Map<String, Object> row = (Map<String, Object>) obj;
			IncomeListDisplay incomeListDisplay = new IncomeListDisplay();
			incomeListDisplay.setId((Long) row.get("id"));
			incomeListDisplay
					.setCustomerName((String) row.get("customer_name"));
			incomeListDisplay.setTotal((BigDecimal) row.get("total"));
			incomeListDisplay.setInvoiceNumber((String) row.get("invoice_no"));
			incomeListDisplay.setDate((Date) row.get("date"));
			incomeListDisplay.setDueDate((Date) row.get("due_date"));
			lst.add(incomeListDisplay);
		}

		BigInteger cnt = (BigInteger) this.getSession()
				.createSQLQuery(countSqlQuery).uniqueResult();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(lst);
		pr.setRowCount(cnt.intValue());
		return pr;
	}

	public IncomeForm getIncomeForm(Long id) {
		String hqlQuery = String
				.format("select new map(income.id as id,income.customer.id as customerId,income.notes as notes,income.invoiceNo as invoiceNumber,"
						+ " income.date as date,income.dueDate as dueDate,income.termOfPayment.id as termOfPaymentId)"
						+ " from %s income left join income.customer"
						+ " left join income.termOfPayment "
						+ " where income.id = :id", Income.class.getName());
		Query query = this.getSession().createQuery(hqlQuery);
		Map<String, Object> map = (Map<String, Object>) query.setLong("id", id)
				.uniqueResult();
		IncomeForm incomeForm = new IncomeForm();
		incomeForm.setCustomerId((Long) map.get("customerId"));
		incomeForm.setDate((Date) map.get("date"));
		incomeForm.setDueDate((Date) map.get("dueDate"));
		incomeForm.setIncomeId(id);
		incomeForm.setInvoiceNo((String) map.get("invoiceNumber"));
		incomeForm.setNotes((String) map.get("notes"));
		incomeForm.setTermOfPaymentId((Long) map.get("termOfPaymentId"));
		incomeForm.setIncomeId(id);
		incomeForm
				.setIncomeDetailForms(this.getIncomeDetailFormByExpenseId(id));

		return incomeForm;
	}

	public List<IncomeDetailForm> getIncomeDetailFormByExpenseId(Long incomeId) {
		List<IncomeDetailForm> incomeDetailForms = new ArrayList<IncomeDetailForm>();
		String hqlQuery = String
				.format("select new map(incomeDetail.id as id,incomeDetail.product.id as productId, incomeDetail.description as description,incomeDetail.quantity as quantity,"
						+ " incomeDetail.unitPrice as unitPrice,incomeDetail.lineTotal as lineTotal)"
						+ " from %s incomeDetail left join incomeDetail.product left join incomeDetail.income"
						+ " where incomeDetail.income.id = :incomeId",
						IncomeDetail.class.getName());
		Query query = this.getSession().createQuery(hqlQuery);
		List list = query.setLong("incomeId", incomeId).list();

		for (Object obj : list) {
			Map<String, Object> row = (Map<String, Object>) obj;
			IncomeDetailForm incomeDetailForm = new IncomeDetailForm();
			incomeDetailForm.setDescription((String) row.get("description"));
			incomeDetailForm.setIncomeDetailId((Long) row.get("id"));
			incomeDetailForm.setIncomeId(incomeId);
			incomeDetailForm.setLineTotal((BigDecimal) row.get("lineTotal"));
			incomeDetailForm.setProductId((Long) row.get("productId"));
			incomeDetailForm.setQuantity((BigDecimal) row.get("quantity"));
			incomeDetailForm.setTax(new BigDecimal(0));
			incomeDetailForm.setUnitPrice((BigDecimal) row.get("unitPrice"));

			incomeDetailForms.add(incomeDetailForm);
		}

		return incomeDetailForms;
	}
}
