package com.suwadi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "beneficiaries")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Beneficiary extends DomainObject {
	private Long id;
	private String memberNo;
	private String firstName;
	private String lastName;
	private String nicNo;
	private String address;
	private Date dateOfBirth;
	private Gender gender;
	private MaritalStatus maritalStatus;
	private SpecialIdentification specialIdentification;
	private BeneficiaryType beneficiaryType;
	private List<BeneficiaryFamilyMember> familyMembers;

	private Society society;
	private Group group;

	// transient variable
	private String fullName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
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

	public String getNicNo() {
		return nicNo;
	}

	public void setNicNo(String nicNo) {
		this.nicNo = nicNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Enumerated(EnumType.ORDINAL)
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Enumerated(EnumType.ORDINAL)
	public SpecialIdentification getSpecialIdentification() {
		return specialIdentification;
	}

	public void setSpecialIdentification(
			SpecialIdentification specialIdentification) {
		this.specialIdentification = specialIdentification;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	public Society getSociety() {
		return society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Enumerated(EnumType.ORDINAL)
	public BeneficiaryType getBeneficiaryType() {
		return beneficiaryType;
	}

	public void setBeneficiaryType(BeneficiaryType beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}

	@OneToMany(mappedBy = "beneficiary", fetch = FetchType.LAZY)
	public List<BeneficiaryFamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(List<BeneficiaryFamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}

	@Transient
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Beneficiary) {
				Beneficiary other = (Beneficiary) obj;
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
