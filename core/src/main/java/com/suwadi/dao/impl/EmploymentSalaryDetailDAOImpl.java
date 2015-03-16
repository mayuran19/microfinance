package com.suwadi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentSalaryDetailDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentSalaryDetail;

@Repository("employmentSalaryDetailDAO")
public class EmploymentSalaryDetailDAOImpl extends
		GenericHibernateDAOSupport<EmploymentSalaryDetail> implements
		EmploymentSalaryDetailDAO {

	public EmploymentSalaryDetailDAOImpl() {
		super(EmploymentSalaryDetail.class);
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentSalaryDetail> findByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId) {
		String hqlQuery = String
				.format("select employmentSalaryDetail from %s employmentSalaryDetail where employmentSalaryDetail.employmentSalaryHeader.id = ?",
						EmploymentSalaryDetail.class.getName());

		return this.getHibernateTemplate().find(hqlQuery,
				employmentSalaryHeaderId);
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentSalaryDetail> findAllEarningsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId) {
		String hqlQuery = String
				.format("select employmentSalaryDetail from %s employmentSalaryDetail where employmentSalaryDetail.employmentSalaryHeader.id = ? and employmentSalaryDetail.employmentPayType.id is not null",
						EmploymentSalaryDetail.class.getName());

		return this.getHibernateTemplate().find(hqlQuery,
				employmentSalaryHeaderId);
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentSalaryDetail> findAllReductionsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId) {
		String hqlQuery = String
				.format("select employmentSalaryDetail from %s employmentSalaryDetail where employmentSalaryDetail.employmentSalaryHeader.id = ? and employmentSalaryDetail.employmentPayReduction.id is not null",
						EmploymentSalaryDetail.class.getName());

		return this.getHibernateTemplate().find(hqlQuery,
				employmentSalaryHeaderId);
	}
}
