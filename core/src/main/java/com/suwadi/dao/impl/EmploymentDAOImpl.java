package com.suwadi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.domain.EmploymentPayType;

@Repository("employmentDAO")
public class EmploymentDAOImpl extends GenericHibernateDAOSupport<Employment>
		implements EmploymentDAO {

	public EmploymentDAOImpl() {
		super(Employment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Employment> findByEmployeeId(Long employeeId) {
		String hqlQuery = String
				.format("select employment from %s employment where employment.employee.id = ?",
						Employment.class.getName());
		return this.getHibernateTemplate().find(hqlQuery, employeeId);
	}

	@SuppressWarnings("unchecked")
	public Employment findActiveEmploymentByEmployeeId(Long employeeId) {
		String hqlQuery = String
				.format("select employment from %s employment left join fetch employment.employmentStatus left join fetch employment.paySchedule where employment.employee.id = ? and employment.isActive = true",
						Employment.class.getName());
		List<Employment> employments = this.getHibernateTemplate().find(
				hqlQuery, employeeId);
		if (employments != null && employments.size() > 0) {
			return employments.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Employment> findAllByPayScheduleId(Long payScheduleId) {
		String hqlQuery = String
				.format("select employment from %s employment left join fetch employment.employee where employment.paySchedule.id = ? and employment.employmentStatus.id = 1",
						Employment.class.getName());
		List<Employment> employments = this.getHibernateTemplate().find(
				hqlQuery, payScheduleId);
		return employments;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayType> findEmploymentPayTypesByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select employmentPaytype from %s employmentPaytype left join fetch employmentPaytype.payType where employmentPaytype.employment.id = ?",
						EmploymentPayType.class.getName());
		List<EmploymentPayType> payTypes = this.getHibernateTemplate().find(
				hqlQuery, employmentId);
		return payTypes;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayType> findEmploymentFixedPayTypesByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select employmentPayType from %s employmentPayType left join fetch employmentPayType.payType where employmentPayType.employment.id = ? and employmentPayType.payType.isUserEnteredAmount = 1",
						EmploymentPayType.class.getName());
		List<EmploymentPayType> payTypes = this.getHibernateTemplate().find(
				hqlQuery, employmentId);
		return payTypes;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayType> findEmploymentPercentagePayTypesByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select employmentPayType from %s employmentPayType left join fetch employmentPayType.payType where employmentPayType.employment.id = ? and employmentPayType.payType.isUserEnteredAmount = 0",
						EmploymentPayType.class.getName());
		List<EmploymentPayType> payTypes = this.getHibernateTemplate().find(
				hqlQuery, employmentId);
		return payTypes;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayReduction> findEmploymentPayReductionsByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select employmentPayRedution from %s employmentPayRedution left join fetch employmentPayRedution.payReduction where employmentPayRedution.employment.id = ?",
						EmploymentPayReduction.class.getName());
		List<EmploymentPayReduction> payReductions = this
				.getHibernateTemplate().find(hqlQuery, employmentId);
		return payReductions;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayReduction> findEmploymentFixedPayReductionsByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select employmentPayRedution from %s employmentPayRedution left join fetch employmentPayRedution.payReduction where employmentPayRedution.employment.id = ? and employmentPayRedution.payReduction.isUserEnteredAmount = 1",
						EmploymentPayReduction.class.getName());
		List<EmploymentPayReduction> payReductions = this
				.getHibernateTemplate().find(hqlQuery, employmentId);
		return payReductions;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayReduction> findEmploymentPercentagePayReductionsByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select employmentPayRedution from %s employmentPayRedution left join fetch employmentPayRedution.payReduction where employmentPayRedution.employment.id = ? and employmentPayRedution.payReduction.isUserEnteredAmount = 0",
						EmploymentPayReduction.class.getName());
		List<EmploymentPayReduction> payReductions = this
				.getHibernateTemplate().find(hqlQuery, employmentId);
		return payReductions;
	}

}
