package com.suwadi.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "acc_expenses")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Expense extends DomainObject {
	private Long id;
	private Vendor vendor;
	private String notes;
	private String invoiceNumber;
	private Date date;
	private Date dueDate;
	private String termsOfPayment;
	private String purchaseOrder;
	private BigDecimal total = new BigDecimal(0);
	private BigDecimal paidAmount = new BigDecimal(0);
	private List<ExpenseDetail> expenseDetails = new ArrayList<ExpenseDetail>();
	private String chequeNo;

	// transient variables
	private BigDecimal dueAmount;
	private List<UploadDocument> documentList;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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

	public String getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public BigDecimal getTotal() {
		return this.total;
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

	@Transient
	public BigDecimal getDueAmount() {
		return this.total.subtract(this.paidAmount);
	}

	@OneToMany(fetch = FetchType.LAZY)
	// @Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	// org.hibernate.annotations.CascadeType.MERGE })
	@JoinColumn(name = "expense_id", insertable = false, updatable = false)
	public List<ExpenseDetail> getExpenseDetails() {
		return expenseDetails;
	}

	public void setExpenseDetails(List<ExpenseDetail> expenseDetails) {
		this.expenseDetails = expenseDetails;
	}

	@Transient
	public List<UploadDocument> getDocumentList() {
		return (this.documentList == null) ? new ArrayList<UploadDocument>()
				: this.documentList;
	}

	public void setDocumentList(List<UploadDocument> documentList) {
		this.documentList = documentList;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Expense) {
				Expense other = (Expense) obj;
				if (this.getId().longValue() == other.getId().longValue()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
