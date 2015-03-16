package com.suwadi.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "districts")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class District extends DomainObject {
	private Long id;
	private String name;
	private String description;
	private List<Profile> profiles;
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

	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
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
			if (obj instanceof District) {
				District other = (District) obj;
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

	@Override
	public String toString() {
//		final BeanWrapper wrapper = new BeanWrapperImpl(this);
//		for (final PropertyDescriptor descriptor : wrapper
//				.getPropertyDescriptors()) {
//			try {
//				// if we call profiles, it will throw the lazyinitialization
//				// exception
//				if (!descriptor.getName().equals("profiles")) {
//					System.out.println(descriptor.getName() + ":"
//							+ descriptor.getReadMethod().invoke(this, null));
//				}
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
