package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "divisions")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Division extends DomainObject {
	private Long id;
	private String name;
	private String description;
	private District district;
	private Double area;
	private Long population;

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
	@JoinColumn(name = "district_id")
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Division) {
				Division other = (Division) obj;
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
