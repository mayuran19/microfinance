package com.suwadi.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity(name = "societies")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Society extends DomainObject {
	private Long id;
	private String name;
	private String description;
	private Double allowedInterestRate;
	private Double shareInterestRate;
	private Beneficiary president;
	private Beneficiary secretary;
	private Beneficiary treasurer;
	private GNDivision gnDivision;
	private String registeredNo;
	private String address;
	private BigDecimal savingAccountInterest;

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

	public Double getAllowedInterestRate() {
		return allowedInterestRate;
	}

	public void setAllowedInterestRate(Double allowedInterestRate) {
		this.allowedInterestRate = allowedInterestRate;
	}

	public Double getShareInterestRate() {
		return shareInterestRate;
	}

	public void setShareInterestRate(Double shareInterestRate) {
		this.shareInterestRate = shareInterestRate;
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

	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "secretary_beneficiary_id")
	public Beneficiary getSecretary() {
		return secretary;
	}

	public void setSecretary(Beneficiary secretary) {
		this.secretary = secretary;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "treasurer_beneficiary_id")
	public Beneficiary getTreasurer() {
		return treasurer;
	}

	public void setTreasurer(Beneficiary treasurer) {
		this.treasurer = treasurer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gn_division_id")
	public GNDivision getGnDivision() {
		return gnDivision;
	}

	public void setGnDivision(GNDivision gnDivision) {
		this.gnDivision = gnDivision;
	}

	public String getRegisteredNo() {
		return registeredNo;
	}

	public void setRegisteredNo(String registeredNo) {
		this.registeredNo = registeredNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Society) {
				Society other = (Society) obj;
				if (this.getId() != null
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

	public BigDecimal getSavingAccountInterest() {
		return savingAccountInterest;
	}

	public void setSavingAccountInterest(BigDecimal savingAccountInterest) {
		this.savingAccountInterest = savingAccountInterest;
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
