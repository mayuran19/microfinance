package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.EmploymentDAO;
import com.suwadi.dao.EmploymentPayReductionDAO;
import com.suwadi.dao.PayReductionDAO;
import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.domain.EmploymentPayTax;
import com.suwadi.domain.SalaryType;
import com.suwadi.service.EmploymentPayReductionService;
import com.suwadi.web.model.payroll.EmploymentPayReductionDetailForm;
import com.suwadi.web.model.payroll.EmploymentPayReductionHeaderForm;
import com.suwadi.web.model.payroll.EmploymentPayTaxDetail;
import com.suwadi.web.model.payroll.EmploymentPayTaxHeader;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentPayReductionService")
public class EmploymentPayReductionServiceImpl implements
		EmploymentPayReductionService {
	private EmploymentPayReductionDAO employmentPayReductionDAO;
	private EmploymentDAO employmentDAO;
	private PayReductionDAO payReductionDAO;

	@Autowired
	public void setEmploymentPayReductionDAO(
			EmploymentPayReductionDAO employmentPayReductionDAO) {
		this.employmentPayReductionDAO = employmentPayReductionDAO;
	}

	@Autowired
	public void setEmploymentDAO(EmploymentDAO employmentDAO) {
		this.employmentDAO = employmentDAO;
	}

	@Autowired
	public void setPayReductionDAO(PayReductionDAO payReductionDAO) {
		this.payReductionDAO = payReductionDAO;
	}

	public EmploymentPayReduction save(EmploymentPayReduction t) {
		return this.employmentPayReductionDAO.save(t);
	}

	public EmploymentPayReduction update(EmploymentPayReduction t) {
		return this.employmentPayReductionDAO.update(t);
	}

	public EmploymentPayReduction findById(Long id) {
		return this.employmentPayReductionDAO.findById(id);
	}

	public EmploymentPayReduction findById(Long id, String... include) {
		return this.employmentPayReductionDAO.findById(id, include);
	}

	public EmploymentPayReduction delete(EmploymentPayReduction t) {
		return this.employmentPayReductionDAO.delete(t);
	}

	public List<EmploymentPayReduction> findAll() {
		return this.employmentPayReductionDAO.findAll();
	}

	public List<EmploymentPayReduction> findAll(Pager pager) {
		return this.employmentPayReductionDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentPayReductionDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentPayReductionDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentPayReductionDAO.isUnique(id, fieldName,
				fieldValue);
	}

	public Map<Long, BigDecimal> getFixedPayReductionAmountMapByEmploymentId(
			Long employmentId) {
		return this.employmentPayReductionDAO
				.getFixedPayReductionAmountMapByEmploymentId(employmentId);
	}

	public List<EmploymentPayReduction> findEmploymentPayReductionsByEmploymentId(
			Long employmentId) {
		return this.employmentPayReductionDAO
				.findEmploymentPayReductionsByEmploymentId(employmentId);
	}

	public void addPayReductionsToEmployment(
			EmploymentPayReductionHeaderForm employmentPayReductionHeaderForm) {
		Employment employment = this.employmentDAO
				.findById(employmentPayReductionHeaderForm.getEmploymentId());
		List<EmploymentPayReductionDetailForm> employmentPayReductionDetailForms = employmentPayReductionHeaderForm
				.getEmploymentPayReductionDetailForms();
		List<EmploymentPayReduction> existingPayReductions = this
				.findEmploymentPayReductionsByEmploymentId(employmentPayReductionHeaderForm
						.getEmploymentId());
		ListIterator<EmploymentPayReductionDetailForm> lstItrEmploymentPayReductionDetails = employmentPayReductionDetailForms
				.listIterator();
		while (lstItrEmploymentPayReductionDetails.hasNext()) {
			EmploymentPayReductionDetailForm tmpEmploymentPayReductionDetail = lstItrEmploymentPayReductionDetails
					.next();
			if (tmpEmploymentPayReductionDetail.getPayReductionId() != null
					&& tmpEmploymentPayReductionDetail.getAmount() != null) {
				ListIterator<EmploymentPayReduction> lstIterator = existingPayReductions
						.listIterator();
				while (lstIterator.hasNext()) {
					EmploymentPayReduction employmentPayReduction = lstIterator
							.next();
					if (employmentPayReduction.getPayReduction().getId()
							.longValue() == tmpEmploymentPayReductionDetail
							.getPayReductionId().longValue()) {
						// update the row and remove it from array list
						employmentPayReduction
								.setAmount(tmpEmploymentPayReductionDetail
										.getAmount());
						employmentPayReduction
								.setSalaryType(tmpEmploymentPayReductionDetail
										.getSalaryType());
						this.update(employmentPayReduction);
						lstIterator.remove();
						lstItrEmploymentPayReductionDetails.remove();
						break;
					}
				}

			}
		}
		// adding items
		lstItrEmploymentPayReductionDetails = employmentPayReductionDetailForms
				.listIterator();
		while (lstItrEmploymentPayReductionDetails.hasNext()) {
			EmploymentPayReductionDetailForm tmpEmploymentPayRedution = lstItrEmploymentPayReductionDetails
					.next();
			if (tmpEmploymentPayRedution.getPayReductionId() != null
					&& tmpEmploymentPayRedution.getAmount() != null) {
				EmploymentPayReduction newEmploymentPayRedution = new EmploymentPayReduction();
				newEmploymentPayRedution.setAmount(tmpEmploymentPayRedution
						.getAmount());
				newEmploymentPayRedution.setEmployment(employment);
				newEmploymentPayRedution
						.setPayReduction(this.payReductionDAO
								.findById(tmpEmploymentPayRedution
										.getPayReductionId()));
				newEmploymentPayRedution.setSalaryType(tmpEmploymentPayRedution
						.getSalaryType());
				this.save(newEmploymentPayRedution);
			}
		}

		// delete item
		ListIterator<EmploymentPayReduction> lstIterator = existingPayReductions
				.listIterator();
		while (lstIterator.hasNext()) {
			EmploymentPayReduction employmentPayRedution = lstIterator.next();
			this.delete(employmentPayRedution);
		}
	}

	public Map<Long, SalaryType> getFixedPayReductionSalaryTypeMapByEmploymentId(
			Long employmentId) {
		return this.employmentPayReductionDAO
				.getFixedPayReductionSalaryTypeMapByEmploymentId(employmentId);
	}

}
