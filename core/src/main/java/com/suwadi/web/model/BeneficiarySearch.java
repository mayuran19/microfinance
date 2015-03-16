package com.suwadi.web.model;

import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.domain.GNDivision;
import com.suwadi.domain.Group;
import com.suwadi.domain.Society;

public class BeneficiarySearch {
	private String firstName;
	private String lastName;
	private String nicNo;
	private String memberNo;
	private Society society;
	private District district;
	private Division division;
	private GNDivision gnDivision;
	private Group group;

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

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Society getSociety() {
		return society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public GNDivision getGnDivision() {
		return gnDivision;
	}

	public void setGnDivision(GNDivision gnDivision) {
		this.gnDivision = gnDivision;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


}
