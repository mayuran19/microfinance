package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "acc_customer_categories")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class CustomerCatagory extends DomainObject {
	private Long id;
	private String name;
	private String description;
	private CustomerCatagory parentCatagory;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_customer_catogory_id")
	public CustomerCatagory getParentCatagory() {
		return parentCatagory;
	}

	public void setParentCatagory(CustomerCatagory parentCatagory) {
		this.parentCatagory = parentCatagory;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof CustomerCatagory) {
				CustomerCatagory other = (CustomerCatagory) obj;
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
