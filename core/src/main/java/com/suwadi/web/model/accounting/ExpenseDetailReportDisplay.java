package com.suwadi.web.model.accounting;

import java.math.BigDecimal;

public class ExpenseDetailReportDisplay {
	private Long expenseDetailId;
	private BigDecimal quantity;
	private BigDecimal total;
	private BigDecimal unitPrice;
	private String productName;

	public Long getExpenseDetailId() {
		return expenseDetailId;
	}

	public void setExpenseDetailId(Long expenseDetailId) {
		this.expenseDetailId = expenseDetailId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
