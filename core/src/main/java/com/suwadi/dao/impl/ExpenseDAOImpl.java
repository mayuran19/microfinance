package com.suwadi.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.ExpenseDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Expense;
import com.suwadi.domain.ExpenseDetail;
import com.suwadi.utils.spring.CustomPropertyPlaceholderConfigurer;
import com.suwadi.web.model.accounting.ExpenseDetailForm;
import com.suwadi.web.model.accounting.ExpenseDetailReportDisplay;
import com.suwadi.web.model.accounting.ExpenseForm;
import com.suwadi.web.model.accounting.ExpenseListDisplay;
import com.suwadi.web.model.accounting.ExpenseReportDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("expenseDAO")
@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class ExpenseDAOImpl extends GenericHibernateDAOSupport<Expense>
		implements ExpenseDAO {
	CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer;

	public ExpenseDAOImpl() {
		super(Expense.class);
	}

	@Autowired
	public void setPropertyPlaceholderConfigurer(
			CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer) {
		this.propertyPlaceholderConfigurer = propertyPlaceholderConfigurer;
	}

	@Override
	public PagedResultSet paginate(Pager pager) {
		List<Expense> pagedResultSet = this.findAll(pager);
		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(pagedResultSet);
		pr.setRowCount(this.getAllCount());
		return pr;
	}

	@Override
	public List<Expense> findAll(Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj left join fetch obj.vendor ",
				Expense.class.getName());
		sb.append(basicQuery);
		if (pager.getSortOrder() == "asc") {
			// need to change this
			sb.append("order by").append(" ").append("obj.id").append(" ")
					.append("asc").append(" ");
		} else {
			sb.append("order by").append(" ").append("obj.id").append(" ")
					.append("desc").append(" ");
		}
		Query query = this.getSession().createQuery(sb.toString())
				.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		return query.list();
	}

	public void updatePayment(BigDecimal payment, Long id) {
		String query = String.format(
				"update %s set paidAmount = :paidAmount where id = :id",
				Expense.class.getName());
		Query hqlQuery = this.getSession().createQuery(query)
				.setDouble("paidAmount", payment.doubleValue())
				.setLong("id", id);
		hqlQuery.executeUpdate();
	}

	public PagedResultSet findAllExpenses(Pager pager) {
		List<ExpenseListDisplay> lst = new ArrayList<ExpenseListDisplay>();
		String sqlQuery = this.propertyPlaceholderConfigurer
				.getProperty("expense.findAllExpenses");
		String countSqlQuery = "select count(distinct acc_expenses.id) from acc_expenses";
		Query query = this.getSession().createSQLQuery(sqlQuery)
				.addScalar("id", Hibernate.LONG)
				.addScalar("total", Hibernate.BIG_DECIMAL)
				.addScalar("payment", Hibernate.BIG_DECIMAL)
				.addScalar("due_amount", Hibernate.BIG_DECIMAL)
				.addScalar("name", Hibernate.STRING)
				.addScalar("date", Hibernate.DATE)
				.addScalar("due_date", Hibernate.DATE)
				.addScalar("invoice_number", Hibernate.STRING)
				.addScalar("purchase_order", Hibernate.STRING)
				.addScalar("cheque_no", Hibernate.STRING);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List results = query.list();
		for (Object obj : results) {
			Map<String, Object> row = (Map<String, Object>) obj;
			ExpenseListDisplay expenseListDisplay = new ExpenseListDisplay();
			expenseListDisplay.setId((Long) row.get("id"));
			expenseListDisplay.setTotal((BigDecimal) row.get("total"));
			expenseListDisplay.setAmountPaid((BigDecimal) row.get("payment"));
			expenseListDisplay.setAmountDue((BigDecimal) row.get("due_amount"));
			expenseListDisplay.setVendorName((String) row.get("name"));
			expenseListDisplay.setDate((Date) row.get("date"));
			expenseListDisplay.setDueDate((Date) row.get("due_date"));
			expenseListDisplay.setInvoiceNumber((String) row
					.get("invoice_number"));
			expenseListDisplay.setPoNumber((String) row.get("purchase_order"));
			lst.add(expenseListDisplay);
		}

		BigInteger cnt = (BigInteger) this.getSession()
				.createSQLQuery(countSqlQuery).uniqueResult();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(lst);
		pr.setRowCount(cnt.intValue());
		return pr;
	}

	public PagedResultSet getExpenseReportDisplayPaginated(Pager pager) {
		StringBuffer sb = new StringBuffer();
		sb.append("select acc_expenses.id as expense_id, acc_expenses.date as date, acc_expenses.invoice_number as invoice_number,");
		sb.append(" acc_expenses.total as total, acc_expenses.paid_amount as paid_amount, acc_vendors.name as vendor_name,");
		sb.append(" acc_expense_details.id as expense_detail_id, acc_expense_details.quantity as quantity, acc_expense_details.unit_price as unit_price,");
		sb.append(" acc_expense_details.total as item_total, acc_products.name as product_name");
		sb.append(" from acc_expenses");
		sb.append(" inner join acc_expense_details on acc_expenses.id = acc_expense_details.expense_id");
		sb.append(" inner join acc_vendors on acc_vendors.id = acc_expenses.vendor_id");
		sb.append(" inner join acc_products on acc_products.id = acc_expense_details.product_id");
		sb.append(" order by acc_expenses.id");
		String sqlQuery = sb.toString();

		StringBuffer sbCount = new StringBuffer();
		sbCount.append("select count(distinct acc_expenses.id) as cnt from acc_expenses");
		sbCount.append(" from acc_expenses inner join acc_expense_details on acc_expenses.id = acc_expense_details.expense_id");
		String countSqlQuery = sbCount.toString();
		Query query = this.getSession().createSQLQuery(sqlQuery)
				.addScalar("expense_id", Hibernate.LONG)
				.addScalar("date", Hibernate.DATE)
				.addScalar("invoice_number", Hibernate.STRING)
				.addScalar("total", Hibernate.BIG_DECIMAL)
				.addScalar("paid_amount", Hibernate.BIG_DECIMAL)
				.addScalar("vendor_name", Hibernate.STRING)
				.addScalar("expense_detail_id", Hibernate.LONG)
				.addScalar("quantity", Hibernate.BIG_DECIMAL)
				.addScalar("unit_price", Hibernate.BIG_DECIMAL)
				.addScalar("item_total", Hibernate.BIG_DECIMAL)
				.addScalar("product_name", Hibernate.STRING);

		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List results = query.list();

		Map<Long, ExpenseReportDisplay> map = new HashMap<Long, ExpenseReportDisplay>();
		for (Object obj : results) {
			Map<String, Object> row = (Map<String, Object>) obj;

			Long expenseId = (Long) row.get("expense_id");
			Date date = (Date) row.get("date");
			String invoiceNumber = (String) row.get("invoice_number");
			BigDecimal total = (BigDecimal) row.get("total");
			BigDecimal paidAmount = (BigDecimal) row.get("paid_amount");
			String vendorName = (String) row.get("vendor_name");
			Long expenseDetailId = (Long) row.get("expense_detail_id");
			BigDecimal quantity = (BigDecimal) row.get("quantity");
			BigDecimal unitPrice = (BigDecimal) row.get("unit_price");
			BigDecimal itemTotal = (BigDecimal) row.get("item_total");
			String productName = (String) row.get("product_name");
			if (map.containsKey(expenseId)) {
				ExpenseReportDisplay expenseReportDisplay = map.get(expenseId);

				ExpenseDetailReportDisplay expenseDetailReportDisplay = new ExpenseDetailReportDisplay();
				expenseDetailReportDisplay.setExpenseDetailId(expenseDetailId);
				expenseDetailReportDisplay.setProductName(productName);
				expenseDetailReportDisplay.setQuantity(quantity);
				expenseDetailReportDisplay.setTotal(itemTotal);
				expenseDetailReportDisplay.setUnitPrice(unitPrice);

				expenseReportDisplay.getExpenseDetailReportDisplays().add(
						expenseDetailReportDisplay);
			} else {
				ExpenseReportDisplay expenseReportDisplay = new ExpenseReportDisplay();
				expenseReportDisplay.setExpenseId(expenseId);
				expenseReportDisplay.setDate(date);
				expenseReportDisplay.setInvoiceNumber(invoiceNumber);
				expenseReportDisplay.setPaidAmount(paidAmount);
				expenseReportDisplay.setTotal(total);
				expenseReportDisplay.setVendorName(vendorName);

				ExpenseDetailReportDisplay expenseDetailReportDisplay = new ExpenseDetailReportDisplay();
				expenseDetailReportDisplay.setExpenseDetailId(expenseDetailId);

				expenseReportDisplay.getExpenseDetailReportDisplays().add(
						expenseDetailReportDisplay);
				map.put(expenseId, expenseReportDisplay);
			}

		}

		BigInteger cnt = (BigInteger) this.getSession()
				.createSQLQuery(countSqlQuery).uniqueResult();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(new ArrayList(map.values()));
		pr.setRowCount(cnt.intValue());
		return pr;
	}

//	public PagedResultSet getExpenseReportDisplay(Pager pager) {
//		StringBuffer expenseSb = new StringBuffer();
//		expenseSb
//				.append("select acc_expenses.id as expense_id, acc_expenses.date as date, acc_expenses.invoice_number as invoice_number,");
//		expenseSb
//				.append(" acc_expenses.total as total, acc_expenses.paid_amount as paid_amount, acc_vendors.name as vendor_name,");
//		expenseSb.append(" from acc_expenses");
//		expenseSb
//				.append(" inner join acc_vendors on acc_vendors.id = acc_expenses.vendor_id");
//	}

	public ExpenseForm getExpenseForm(Long id) {
		String hqlQuery = String
				.format("select new map(expense.id as id,expense.vendor.id as vendorId,expense.notes as notes,expense.invoiceNumber as invoiceNumber,"
						+ "expense.date as date,expense.dueDate as dueDate,expense.termsOfPayment as termsOfPayment,expense.purchaseOrder as purchaseOrder,"
						+ "expense.total as total,expense.paidAmount as paidAmount) from %s expense left join expense.vendor "
						+ "where expense.id = :id", Expense.class.getName());
		Query query = this.getSession().createQuery(hqlQuery);
		Map<String, Object> map = (Map<String, Object>) query.setLong("id", id)
				.uniqueResult();
		ExpenseForm expenseForm = new ExpenseForm();
		expenseForm.setId(id);
		expenseForm.setDate((Date) map.get("date"));
		expenseForm.setDueDate((Date) map.get("dueDate"));
		expenseForm.setInvoiceNumber((String) map.get("invoiceNumber"));
		expenseForm.setNotes((String) map.get("notes"));
		expenseForm.setPurchaseOrder((String) map.get("purchaseOrder"));
		expenseForm.setVendorId((Long) map.get("vendorId"));
		expenseForm.setExpenseDetails(this.getExpenseDetailFormByExpenseId(id));

		return expenseForm;
	}

	public List<ExpenseDetailForm> getExpenseDetailFormByExpenseId(
			Long expenseId) {
		List<ExpenseDetailForm> expenseDetailForms = new ArrayList<ExpenseDetailForm>();
		String hqlQuery = String
				.format("select new map(expenseDetail.id as id,expenseDetail.product.id as productId, expenseDetail.expenseAccount.id as expenseAccountId, expenseDetail.description as description,expenseDetail.quantity as quantity,"
						+ " expenseDetail.unitPrice as unitPrice,expenseDetail.total as total)"
						+ " from %s expenseDetail left join expenseDetail.product left join expenseDetail.expenseAccount left join expenseDetail.expense"
						+ " where expenseDetail.expense.id = :expenseId",
						ExpenseDetail.class.getName());
		Query query = this.getSession().createQuery(hqlQuery);
		List list = query.setLong("expenseId", expenseId).list();

		for (Object obj : list) {
			Map<String, Object> row = (Map<String, Object>) obj;
			ExpenseDetailForm expenseDetailForm = new ExpenseDetailForm();
			expenseDetailForm.setDescription((String) row.get("description"));
			expenseDetailForm.setExpenseAccountId((Long) row
					.get("expenseAccountId"));
			expenseDetailForm.setId((Long) row.get("id"));
			expenseDetailForm.setProductId((Long) row.get("productId"));
			expenseDetailForm.setQuantity((BigDecimal) row.get("quantity"));
			expenseDetailForm.setTotal((BigDecimal) row.get("total"));
			expenseDetailForm.setUnitPrice((BigDecimal) row.get("unitPrice"));

			expenseDetailForms.add(expenseDetailForm);
		}

		return expenseDetailForms;
	}

}
