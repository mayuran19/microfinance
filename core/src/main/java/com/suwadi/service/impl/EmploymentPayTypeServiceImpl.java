package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.EmploymentDAO;
import com.suwadi.dao.EmploymentPayTypeDAO;
import com.suwadi.dao.PayTypeDAO;
import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentPayType;
import com.suwadi.domain.PayType;
import com.suwadi.domain.SalaryType;
import com.suwadi.service.EmploymentPayTypeService;
import com.suwadi.web.model.payroll.EmploymentPayTypeForm;
import com.suwadi.web.model.payroll.EmploymentPayTypesForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentPayTypeService")
public class EmploymentPayTypeServiceImpl implements EmploymentPayTypeService {
	private EmploymentPayTypeDAO employmentPayTypeDAO;
	private EmploymentDAO employmentDAO;
	private PayTypeDAO payTypeDAO;

	@Autowired
	public void setEmploymentPayTypeDAO(
			EmploymentPayTypeDAO employmentPayTypeDAO) {
		this.employmentPayTypeDAO = employmentPayTypeDAO;
	}

	@Autowired
	public void setEmploymentDAO(EmploymentDAO employmentDAO) {
		this.employmentDAO = employmentDAO;
	}

	@Autowired
	public void setPayTypeDAO(PayTypeDAO payTypeDAO) {
		this.payTypeDAO = payTypeDAO;
	}

	public EmploymentPayType save(EmploymentPayType t) {
		return this.employmentPayTypeDAO.save(t);
	}

	public EmploymentPayType update(EmploymentPayType t) {
		return this.employmentPayTypeDAO.update(t);
	}

	public EmploymentPayType findById(Long id) {
		return this.employmentPayTypeDAO.findById(id);
	}

	public EmploymentPayType findById(Long id, String... include) {
		return this.employmentPayTypeDAO.findById(id, include);
	}

	public EmploymentPayType delete(EmploymentPayType t) {
		return this.employmentPayTypeDAO.delete(t);
	}

	public List<EmploymentPayType> findAll() {
		return this.employmentPayTypeDAO.findAll();
	}

	public List<EmploymentPayType> findAll(Pager pager) {
		return this.employmentPayTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentPayTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentPayTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentPayTypeDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<PayType> findPayTypeByEmploymentId(Long employmentId) {
		return this.employmentPayTypeDAO
				.findPayTypeByEmploymentId(employmentId);
	}

	@Transactional
	public void addPayTypesToEmployment(
			EmploymentPayTypesForm employmentPayTypesForm) {
		Employment employment = this.employmentDAO
				.findById(employmentPayTypesForm.getEmploymentId());
		List<EmploymentPayTypeForm> employmentPayTypeFormList = employmentPayTypesForm
				.getEmploymentPayTypeForm();
		List<EmploymentPayType> existingPayTypes = this
				.findEmploymentPayTypeByEmploymentId(employmentPayTypesForm
						.getEmploymentId());
		ListIterator<EmploymentPayTypeForm> lstItrEmploymentPayTypeForm = employmentPayTypeFormList
				.listIterator();
		while (lstItrEmploymentPayTypeForm.hasNext()) {
			EmploymentPayTypeForm tmpEmploymentPayTypeForm = lstItrEmploymentPayTypeForm
					.next();
			if (tmpEmploymentPayTypeForm.getPayTypeId() != null
					&& tmpEmploymentPayTypeForm.getAmount() != null) {
				ListIterator<EmploymentPayType> lstIterator = existingPayTypes
						.listIterator();
				while (lstIterator.hasNext()) {
					EmploymentPayType employmentPayType = lstIterator.next();
					if (employmentPayType.getPayType().getId().longValue() == tmpEmploymentPayTypeForm
							.getPayTypeId().longValue()) {
						// update the row and remove it from array list
						employmentPayType.setAmount(tmpEmploymentPayTypeForm
								.getAmount());
						employmentPayType
								.setSalaryType(tmpEmploymentPayTypeForm
										.getSalaryType());
						this.update(employmentPayType);
						lstIterator.remove();
						lstItrEmploymentPayTypeForm.remove();
						break;
					}
				}

			}
		}
		// adding items
		lstItrEmploymentPayTypeForm = employmentPayTypeFormList.listIterator();
		while (lstItrEmploymentPayTypeForm.hasNext()) {
			EmploymentPayTypeForm tmpEmploymentPayTypeForm = lstItrEmploymentPayTypeForm
					.next();
			if (tmpEmploymentPayTypeForm.getPayTypeId() != null
					&& tmpEmploymentPayTypeForm.getAmount() != null) {
				EmploymentPayType newEmploymentPayType = new EmploymentPayType();
				newEmploymentPayType.setAmount(tmpEmploymentPayTypeForm
						.getAmount());
				newEmploymentPayType.setEmployment(employment);
				newEmploymentPayType.setPayType(this.payTypeDAO
						.findById(tmpEmploymentPayTypeForm.getPayTypeId()));
				newEmploymentPayType.setSalaryType(tmpEmploymentPayTypeForm
						.getSalaryType());
				this.save(newEmploymentPayType);
			}
		}

		// delete item
		ListIterator<EmploymentPayType> lstIterator = existingPayTypes
				.listIterator();
		while (lstIterator.hasNext()) {
			EmploymentPayType employmentPayType = lstIterator.next();
			this.delete(employmentPayType);
		}
	}

	public List<EmploymentPayType> findEmploymentPayTypeByEmploymentId(
			Long employmentId) {
		return this.employmentPayTypeDAO
				.findEmploymentPayTypeByEmploymentId(employmentId);
	}

	public Map<Long, BigDecimal> getFixedPayTypeAmountMapByEmploymentId(
			Long employmentId) {
		return this.employmentPayTypeDAO
				.getFixedPayTypeAmountMapByEmploymentId(employmentId);
	}

	public Map<Long, SalaryType> getFixedPayTypeSalaryTypeMapByEmploymentId(
			Long employmentId) {
		return this.employmentPayTypeDAO
				.getFixedPayTypeSalaryTypeMapByEmploymentId(employmentId);
	}
}
