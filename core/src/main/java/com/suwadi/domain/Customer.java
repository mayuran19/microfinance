package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "acc_customers")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Customer extends DomainObject {
	private Long id;
	private String customerName;
	private String address;
	private CustomerCatagory customerCatagory;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_catagory_id")
	public CustomerCatagory getCustomerCatagory() {
		return customerCatagory;
	}

	public void setCustomerCatagory(CustomerCatagory customerCatagory) {
		this.customerCatagory = customerCatagory;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Customer) {
				Customer other = (Customer) obj;
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
