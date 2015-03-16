package com.suwadi.web.model.accounting;

import java.math.BigDecimal;

public class ExpenseDetailForm {
	private Long id;
	private Long productId;
	private Long expenseAccountId;
	private String description;
	private BigDecimal quantity = new BigDecimal(1);
	private BigDecimal unitPrice = new BigDecimal(1);
	private BigDecimal total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getExpenseAccountId() {
		return expenseAccountId;
	}

	public void setExpenseAccountId(Long expenseAccountId) {
		this.expenseAccountId = expenseAccountId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
