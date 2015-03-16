package com.suwadi.web.model.accounting;

import java.math.BigDecimal;

public class IncomeDetailForm {
	private Long incomeDetailId;
	private Long productId;
	private String description;
	private BigDecimal quantity = new BigDecimal(1);
	private BigDecimal unitPrice = new BigDecimal(1);
	private BigDecimal tax;
	private BigDecimal lineTotal;
	private Long incomeId;

	public Long getIncomeDetailId() {
		return incomeDetailId;
	}

	public void setIncomeDetailId(Long incomeDetailId) {
		this.incomeDetailId = incomeDetailId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(BigDecimal lineTotal) {
		this.lineTotal = lineTotal;
	}

	public Long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(Long incomeId) {
		this.incomeId = incomeId;
	}

}
