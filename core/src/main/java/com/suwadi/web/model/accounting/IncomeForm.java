package com.suwadi.web.model.accounting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeForm {
	private Long incomeId;
	private Long customerId;
	private String notes;
	private String invoiceNo;
	private Date date;
	private Date dueDate;
	private Long termOfPaymentId;
	private List<IncomeDetailForm> incomeDetailForms = new ArrayList<IncomeDetailForm>();
	private String chequeNo;

	public Long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(Long incomeId) {
		this.incomeId = incomeId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Long getTermOfPaymentId() {
		return termOfPaymentId;
	}

	public void setTermOfPaymentId(Long termOfPaymentId) {
		this.termOfPaymentId = termOfPaymentId;
	}

	public List<IncomeDetailForm> getIncomeDetailForms() {
		return incomeDetailForms;
	}

	public void setIncomeDetailForms(List<IncomeDetailForm> incomeDetailForms) {
		this.incomeDetailForms = incomeDetailForms;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

}
