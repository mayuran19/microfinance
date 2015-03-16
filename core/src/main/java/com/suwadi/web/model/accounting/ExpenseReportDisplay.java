package com.suwadi.web.model.accounting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseReportDisplay {
	private Long expenseId;
	private Date date;
	private String invoiceNumber;
	private BigDecimal total;
	private BigDecimal paidAmount;
	private String vendorName;
	private List<ExpenseDetailReportDisplay> expenseDetailReportDisplays = new ArrayList<ExpenseDetailReportDisplay>();

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<ExpenseDetailReportDisplay> getExpenseDetailReportDisplays() {
		return expenseDetailReportDisplays;
	}

	public void setExpenseDetailReportDisplays(
			List<ExpenseDetailReportDisplay> expenseDetailReportDisplays) {
		this.expenseDetailReportDisplays = expenseDetailReportDisplays;
	}

}
