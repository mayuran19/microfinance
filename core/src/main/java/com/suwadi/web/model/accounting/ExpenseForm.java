package com.suwadi.web.model.accounting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.suwadi.domain.UploadDocument;

public class ExpenseForm {
	private Long id;
	private Long vendorId;
	private String notes;
	private String invoiceNumber;
	private Date date;
	private Date dueDate;
	private String purchaseOrder;
	private List<ExpenseDetailForm> expenseDetails = new ArrayList<ExpenseDetailForm>();
	// private List<UploadDocument> documentList = new
	// ArrayList<UploadDocument>();
	private BigDecimal dueAmount;
	private BigDecimal paidAmount = new BigDecimal(0);
	private BigDecimal total = new BigDecimal(0);
	private String chequeNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public List<ExpenseDetailForm> getExpenseDetails() {
		return expenseDetails;
	}

	public void setExpenseDetails(List<ExpenseDetailForm> expenseDetails) {
		this.expenseDetails = expenseDetails;
	}

	// public List<UploadDocument> getDocumentList() {
	// return documentList;
	// }
	//
	// public void setDocumentList(List<UploadDocument> documentList) {
	// this.documentList = documentList;
	// }

	public BigDecimal getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

}
