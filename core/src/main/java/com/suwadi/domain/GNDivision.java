package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "gn_divisions")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class GNDivision extends DomainObject {
	private Long id;
	private String name;
	private String description;
	private String gnNumber;
	private Division division;

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

	public String getGnNumber() {
		return gnNumber;
	}

	public void setGnNumber(String gnNumber) {
		this.gnNumber = gnNumber;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "division_id")
	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof GNDivision) {
				GNDivision other = (GNDivision) obj;
				if (this.getId() != null
						&& other.getId() != null
						&& this.getId().longValue() == other.getId()
								.longValue()) {
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

	@Override
	public String toString() {
//		final BeanWrapper wrapper = new BeanWrapperImpl(this);
//		for (final PropertyDescriptor descriptor : wrapper
//				.getPropertyDescriptors()) {
//			try {
//				System.out.println(descriptor.getName() + ":"
//						+ descriptor.getReadMethod().invoke(this, null));
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
//		}
		return super.toString();
	}
}
