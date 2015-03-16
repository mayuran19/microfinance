package com.suwadi.web.model.accounting;

import java.math.BigDecimal;
import java.util.Date;

public class ExpenseListDisplay {
	private Long id;
	private Date date;
	private String poNumber;
	private String vendorName;
	private BigDecimal amountDue = new BigDecimal(0);
	private BigDecimal amountPaid = new BigDecimal(0);;
	private BigDecimal total = new BigDecimal(0);;
	private Date dueDate;
	private String invoiceNumber;
	private String chequeNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public BigDecimal getAmountDue() {
		if (this.amountDue == null) {
			return new BigDecimal(0);
		}
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

	public BigDecimal getAmountPaid() {
		if (this.amountPaid == null) {
			return new BigDecimal(0);
		}
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public BigDecimal getTotal() {
		if (this.total == null) {
			return new BigDecimal(0);
		}
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

}
