package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity(name = "groups")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Group extends DomainObject {
	private Long id;
	private String name;
	private String description;
	private Society society;
	private Beneficiary president;
	private String contactNo;
	private String eccName;
	private Beneficiary eccPresident;

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
	@JoinColumn(name = "society_id")
	public Society getSociety() {
		return society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "president_beneficiary_id")
	public Beneficiary getPresident() {
		return president;
	}

	public void setPresident(Beneficiary president) {
		this.president = president;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEccName() {
		return eccName;
	}

	public void setEccName(String eccName) {
		this.eccName = eccName;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "ecc_president_beneficiary_id")
	public Beneficiary getEccPresident() {
		return eccPresident;
	}

	public void setEccPresident(Beneficiary eccPresident) {
		this.eccPresident = eccPresident;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Group) {
				Group other = (Group) obj;
				if (this.getId() != null && this.getId().longValue() == other.getId().longValue()) {
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
		// final BeanWrapper wrapper = new BeanWrapperImpl(this);
		// for (final PropertyDescriptor descriptor : wrapper
		// .getPropertyDescriptors()) {
		// try {
		// System.out.println(descriptor.getName() + ":"
		// + descriptor.getReadMethod().invoke(this, null));
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// }
		// }
		return super.toString();
	}
}
