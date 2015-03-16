package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.EmploymentDAO;
import com.suwadi.dao.EmploymentPayTaxDAO;
import com.suwadi.dao.PayTaxDAO;
import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentPayTax;
import com.suwadi.domain.EmploymentPayType;
import com.suwadi.service.EmploymentPayTaxService;
import com.suwadi.web.model.payroll.EmploymentPayTaxDetail;
import com.suwadi.web.model.payroll.EmploymentPayTaxHeader;
import com.suwadi.web.model.payroll.EmploymentPayTypeForm;
import com.suwadi.web.model.payroll.EmploymentPayTypesForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentPayTaxService")
public class EmploymentPayTaxServiceImpl implements EmploymentPayTaxService {
	private EmploymentPayTaxDAO employmentPayTaxDAO;
	private EmploymentDAO employmentDAO;
	private PayTaxDAO payTaxDAO;

	@Autowired
	public void setEmploymentPayTaxDAO(EmploymentPayTaxDAO employmentPayTaxDAO) {
		this.employmentPayTaxDAO = employmentPayTaxDAO;
	}

	@Autowired
	public void setEmploymentDAO(EmploymentDAO employmentDAO) {
		this.employmentDAO = employmentDAO;
	}

	@Autowired
	public void setPayTaxDAO(PayTaxDAO payTaxDAO) {
		this.payTaxDAO = payTaxDAO;
	}

	public EmploymentPayTax save(EmploymentPayTax t) {
		return this.employmentPayTaxDAO.save(t);
	}

	public EmploymentPayTax update(EmploymentPayTax t) {
		return this.employmentPayTaxDAO.update(t);
	}

	public EmploymentPayTax findById(Long id) {
		return this.employmentPayTaxDAO.findById(id);
	}

	public EmploymentPayTax findById(Long id, String... include) {
		return this.employmentPayTaxDAO.findById(id, include);
	}

	public EmploymentPayTax delete(EmploymentPayTax t) {
		return this.employmentPayTaxDAO.delete(t);
	}

	public List<EmploymentPayTax> findAll() {
		return this.employmentPayTaxDAO.findAll();
	}

	public List<EmploymentPayTax> findAll(Pager pager) {
		return this.employmentPayTaxDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentPayTaxDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentPayTaxDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentPayTaxDAO.isUnique(id, fieldName, fieldValue);
	}

	public Map<Long, BigDecimal> getFixedPayTaxAmountMapByEmploymentId(
			Long employmentId) {
		return this.employmentPayTaxDAO
				.getFixedPayTaxAmountMapByEmploymentId(employmentId);
	}

	@Transactional
	public void addPayTaxesToEmployment(
			EmploymentPayTaxHeader employmentPayTaxHeader) {
		Employment employment = this.employmentDAO
				.findById(employmentPayTaxHeader.getEmploymentId());
		List<EmploymentPayTaxDetail> employmentPayTaxDetails = employmentPayTaxHeader
				.getEmploymentPayTaxDetails();
		List<EmploymentPayTax> existingPayTaxes = this
				.findEmploymentPayTaxesByEmploymentId(employmentPayTaxHeader
						.getEmploymentId());
		ListIterator<EmploymentPayTaxDetail> lstItrEmploymentPayTaxDetails = employmentPayTaxDetails
				.listIterator();
		while (lstItrEmploymentPayTaxDetails.hasNext()) {
			EmploymentPayTaxDetail tmpEmploymentPayTaxDetail = lstItrEmploymentPayTaxDetails
					.next();
			if (tmpEmploymentPayTaxDetail.getPayTaxId() != null
					&& tmpEmploymentPayTaxDetail.getAmount() != null) {
				ListIterator<EmploymentPayTax> lstIterator = existingPayTaxes
						.listIterator();
				while (lstIterator.hasNext()) {
					EmploymentPayTax employmentPayTax = lstIterator.next();
					if (employmentPayTax.getPayTax().getId().longValue() == tmpEmploymentPayTaxDetail
							.getPayTaxId().longValue()) {
						// update the row and remove it from array list
						employmentPayTax.setAmount(tmpEmploymentPayTaxDetail
								.getAmount());
						this.update(employmentPayTax);
						lstIterator.remove();
						lstItrEmploymentPayTaxDetails.remove();
						break;
					}
				}

			}
		}
		// adding items
		lstItrEmploymentPayTaxDetails = employmentPayTaxDetails.listIterator();
		while (lstItrEmploymentPayTaxDetails.hasNext()) {
			EmploymentPayTaxDetail tmpEmploymentPayTax = lstItrEmploymentPayTaxDetails
					.next();
			if (tmpEmploymentPayTax.getPayTaxId() != null
					&& tmpEmploymentPayTax.getAmount() != null) {
				EmploymentPayTax newEmploymentPayTax = new EmploymentPayTax();
				newEmploymentPayTax.setAmount(tmpEmploymentPayTax.getAmount());
				newEmploymentPayTax.setEmployment(employment);
				newEmploymentPayTax.setPayTax(this.payTaxDAO
						.findById(tmpEmploymentPayTax.getPayTaxId()));
				this.save(newEmploymentPayTax);
			}
		}

		// delete item
		ListIterator<EmploymentPayTax> lstIterator = existingPayTaxes
				.listIterator();
		while (lstIterator.hasNext()) {
			EmploymentPayTax employmentPayTax = lstIterator.next();
			this.delete(employmentPayTax);
		}
	}

	public List<EmploymentPayTax> findEmploymentPayTaxesByEmploymentId(
			Long employmentId) {
		return this.employmentPayTaxDAO
				.findEmploymentPayTaxesByEmploymentId(employmentId);
	}

}
