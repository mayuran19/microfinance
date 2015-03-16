package com.suwadi.web.model;

import java.util.Date;

import com.suwadi.domain.Gender;
import com.suwadi.domain.Group;
import com.suwadi.domain.MaritalStatus;
import com.suwadi.domain.SpecialIdentification;

public class BeneficiaryForm {
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
	private Group group;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public SpecialIdentification getSpecialIdentification() {
		return specialIdentification;
	}

	public void setSpecialIdentification(
			SpecialIdentification specialIdentification) {
		this.specialIdentification = specialIdentification;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
