package com.suwadi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "beneficiary_family_members")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class BeneficiaryFamilyMember extends DomainObject {
	private Long id;
	private String firstName;
	private String lastName;
	private FamilyRelationship familyRelationship;
	private Date dateOfBirth;
	private Gender gender;
	private String nicNo;
	private String occupation;
	private Beneficiary beneficiary;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "family_relationship_id")
	public FamilyRelationship getFamilyRelationship() {
		return familyRelationship;
	}

	public void setFamilyRelationship(FamilyRelationship familyRelationship) {
		this.familyRelationship = familyRelationship;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Enumerated(EnumType.ORDINAL)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getNicNo() {
		return nicNo;
	}

	public void setNicNo(String nicNo) {
		this.nicNo = nicNo;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "beneficiary_id")
	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof BeneficiaryFamilyMember) {
				BeneficiaryFamilyMember other = (BeneficiaryFamilyMember) obj;
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
