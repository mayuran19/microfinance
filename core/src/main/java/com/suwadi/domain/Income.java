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

@Entity(name = "acc_incomes")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Income extends DomainObject {
	private Long id;
	private Customer customer;
	private String notes;
	private String invoiceNo;
	private Date date;
	private Date dueDate;
	private TermOfPayment termOfPayment;
	private List<IncomeDetail> incomeDetails = new ArrayList<IncomeDetail>();
	private String chequeNo;
	private BigDecimal total;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "term_of_payment_id")
	public TermOfPayment getTermOfPayment() {
		return termOfPayment;
	}

	public void setTermOfPayment(TermOfPayment termOfPayment) {
		this.termOfPayment = termOfPayment;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "income_id", insertable = false, updatable = false)
	public List<IncomeDetail> getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(List<IncomeDetail> incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Income) {
				Income other = (Income) obj;
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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
