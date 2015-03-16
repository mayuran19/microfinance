package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.EmployeeDAO;
import com.suwadi.dao.EmploymentDAO;
import com.suwadi.dao.EmploymentStatusDAO;
import com.suwadi.dao.PayScheduleDAO;
import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.domain.EmploymentPayType;
import com.suwadi.domain.EmploymentSalaryDetail;
import com.suwadi.domain.EmploymentSalaryHeader;
import com.suwadi.domain.Payroll;
import com.suwadi.domain.SalaryType;
import com.suwadi.service.ConfigurationService;
import com.suwadi.service.EmploymentSalaryDetailService;
import com.suwadi.service.EmploymentSalaryHeaderService;
import com.suwadi.service.EmploymentService;
import com.suwadi.service.PayrollService;
import com.suwadi.web.model.payroll.EmploymentForm;
import com.suwadi.web.model.payroll.PayrollSelectEmployeesForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentService")
public class EmploymentServiceImpl implements EmploymentService {
	private EmploymentDAO employmentDAO;
	private EmployeeDAO employeeDAO;
	private EmploymentStatusDAO employmentStatusDAO;
	private PayScheduleDAO payScheduleDAO;
	private PayrollService payrollService;
	private EmploymentSalaryHeaderService employmentSalaryHeaderService;
	private ConfigurationService configurationService;
	private EmploymentSalaryDetailService employmentSalaryDetailService;

	@Autowired
	public void setEmploymentDAO(EmploymentDAO employmentDAO) {
		this.employmentDAO = employmentDAO;
	}

	@Autowired
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Autowired
	public void setEmploymentStatusDAO(EmploymentStatusDAO employmentStatusDAO) {
		this.employmentStatusDAO = employmentStatusDAO;
	}

	@Autowired
	public void setPayScheduleDAO(PayScheduleDAO payScheduleDAO) {
		this.payScheduleDAO = payScheduleDAO;
	}

	@Autowired
	public void setPayrollService(PayrollService payrollService) {
		this.payrollService = payrollService;
	}

	@Autowired
	public void setEmploymentSalaryHeaderService(
			EmploymentSalaryHeaderService employmentSalaryHeaderService) {
		this.employmentSalaryHeaderService = employmentSalaryHeaderService;
	}

	@Autowired
	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Autowired
	public void setEmploymentSalaryDetailService(
			EmploymentSalaryDetailService employmentSalaryDetailService) {
		this.employmentSalaryDetailService = employmentSalaryDetailService;
	}

	public Employment save(Employment t) {
		return this.employmentDAO.save(t);
	}

	public Employment update(Employment t) {
		return this.employmentDAO.update(t);
	}

	public Employment findById(Long id) {
		return this.employmentDAO.findById(id);
	}

	public Employment findById(Long id, String... include) {
		return this.employmentDAO.findById(id, include);
	}

	public Employment delete(Employment t) {
		return this.employmentDAO.delete(t);
	}

	public List<Employment> findAll() {
		return this.employmentDAO.findAll();
	}

	public List<Employment> findAll(Pager pager) {
		return this.employmentDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<Employment> findByEmployeeId(Long employeeId) {
		return this.employmentDAO.findByEmployeeId(employeeId);
	}

	public Employment save(EmploymentForm employmentForm) {
		Employment employment = new Employment();
		if (employmentForm.getEmploymentId() != null
				&& employmentForm.getEmploymentId().longValue() != 0) {
			employment = this.findById(employmentForm.getEmploymentId());
		}
		employment.setId(employmentForm.getEmploymentId());
		employment.setEmployee(this.employeeDAO.findById(employmentForm
				.getEmployeeId()));
		employment.setEmploymentStatus(this.employmentStatusDAO
				.findById(employmentForm.getEmploymentStatusId()));
		employment.setHiredDate(employmentForm.getStartDate());
		employment.setTerminationDate(employmentForm.getEndDate());
		employment.setWorkLocation(employmentForm.getWorkLocation());
		employment.setRemarks(employmentForm.getRemarks());
		employment.setIsActive(true);
		employment.setPaySchedule(this.payScheduleDAO.findById(employmentForm
				.getPayScheduleId()));
		if (employmentForm.getEmploymentId() != null
				&& employmentForm.getEmploymentId().longValue() != 0) {
			return this.update(employment);
		} else {
			return this.save(employment);
		}
	}

	public Employment findActiveEmploymentByEmployeeId(Long employeeId) {
		return this.employmentDAO.findActiveEmploymentByEmployeeId(employeeId);
	}

	public List<Employment> findAllByPayScheduleId(Long payScheduleId) {
		return this.employmentDAO.findAllByPayScheduleId(payScheduleId);
	}

	public EmploymentSalaryHeader createEmploymentSalaryHeader(Long payRollId,
			Long payScheduleId, Date fromDate, Date toDate, Long employmentId) {
		Payroll payroll = this.payrollService.findById(payRollId);
		Employment activeEmployment = this.employmentDAO.findById(employmentId,
				"employee");
		List<EmploymentPayType> employmentPayTypes = this.employmentDAO
				.findEmploymentFixedPayTypesByEmploymentId(activeEmployment
						.getId());
		List<EmploymentPayType> percentageEmploymentPayTypes = this.employmentDAO
				.findEmploymentPercentagePayTypesByEmploymentId(activeEmployment
						.getId());
		List<EmploymentPayReduction> employmentPayReductions = this.employmentDAO
				.findEmploymentFixedPayReductionsByEmploymentId(activeEmployment
						.getId());
		List<EmploymentPayReduction> percentageEmploymentPayReductions = this.employmentDAO
				.findEmploymentPercentagePayReductionsByEmploymentId(activeEmployment
						.getId());
		EmploymentPayType basicSalary = null;

		BigDecimal payTypeTotal = new BigDecimal(0);
		for (EmploymentPayType employmentPayType : employmentPayTypes) {
			if (employmentPayType.getPayType().getId().longValue() == (Long
					.valueOf(this.configurationService
							.findValueByKey("payroll.paytype.basicSalary.id"))
					.longValue())) {
				basicSalary = employmentPayType;
			}
			payTypeTotal = payTypeTotal.add(employmentPayType.getAmount());
		}

		BigDecimal percentagePayTypeTotal = new BigDecimal(0);
		for (EmploymentPayType employmentPayType : percentageEmploymentPayTypes) {
			if (employmentPayType.getSalaryType()
					.equals(SalaryType.BasicSalary)) {
				percentagePayTypeTotal = percentagePayTypeTotal
						.add(employmentPayType.getAmount()
								.divide(new BigDecimal(100))
								.multiply(basicSalary.getAmount()));
			} else if (employmentPayType.getSalaryType().equals(
					SalaryType.GrossSalary)) {
				percentagePayTypeTotal = percentagePayTypeTotal
						.add(employmentPayType.getAmount()
								.divide(new BigDecimal(100))
								.multiply(payTypeTotal));
			}
		}
		/*
		 * Pay redutions
		 */
		BigDecimal fixedPayReductionTotal = new BigDecimal(0);
		for (EmploymentPayReduction employmentPayReduction : employmentPayReductions) {
			fixedPayReductionTotal = fixedPayReductionTotal
					.add(employmentPayReduction.getAmount());
		}

		BigDecimal percentagePayReductionTotal = new BigDecimal(0);
		for (EmploymentPayReduction employmentPayReduction : percentageEmploymentPayReductions) {
			if (employmentPayReduction.getSalaryType().equals(
					SalaryType.BasicSalary)) {
				percentagePayReductionTotal = percentagePayReductionTotal
						.add(employmentPayReduction.getAmount()
								.divide(new BigDecimal(100))
								.multiply(basicSalary.getAmount()));
			} else if (employmentPayReduction.getSalaryType().equals(
					SalaryType.GrossSalary)) {
				percentagePayReductionTotal = percentagePayReductionTotal
						.add(employmentPayReduction.getAmount()
								.divide(new BigDecimal(100))
								.multiply(payTypeTotal));
			}
		}

		EmploymentSalaryHeader employmentSalaryHeader = new EmploymentSalaryHeader();
		employmentSalaryHeader.setEmployeeName(activeEmployment.getEmployee()
				.getName());
		employmentSalaryHeader.setEmployeeNicNo(activeEmployment.getEmployee()
				.getNicNo());
		employmentSalaryHeader.setEmployment(activeEmployment);
		employmentSalaryHeader.setGrossPay(payTypeTotal
				.add(percentagePayTypeTotal));
		employmentSalaryHeader.setNetPay(payTypeTotal
				.add(percentagePayTypeTotal).subtract(fixedPayReductionTotal)
				.subtract(percentagePayReductionTotal));
		employmentSalaryHeader.setPayroll(payroll);
		employmentSalaryHeader.setTotalReductions(fixedPayReductionTotal
				.add(percentagePayReductionTotal));
		this.employmentSalaryHeaderService.save(employmentSalaryHeader);

		for (EmploymentPayType employmentPayType : employmentPayTypes) {
			EmploymentSalaryDetail employmentSalaryDetail = new EmploymentSalaryDetail();
			employmentSalaryDetail.setEmploymentPayType(employmentPayType);
			employmentSalaryDetail
					.setEmploymentSalaryHeader(employmentSalaryHeader);
			employmentSalaryDetail.setPayTypeAmount(employmentPayType
					.getAmount());
			employmentSalaryDetail.setPayTypeName(employmentPayType
					.getPayType().getPayType());

			this.employmentSalaryDetailService.save(employmentSalaryDetail);
		}

		for (EmploymentPayType employmentPayType : percentageEmploymentPayTypes) {
			BigDecimal amount = new BigDecimal(0);
			if (employmentPayType.getSalaryType()
					.equals(SalaryType.BasicSalary)) {
				amount = employmentPayType.getAmount()
						.divide(new BigDecimal(100))
						.multiply(basicSalary.getAmount());
			} else if (employmentPayType.getSalaryType().equals(
					SalaryType.GrossSalary)) {
				amount = employmentPayType.getAmount()
						.divide(new BigDecimal(100)).multiply(payTypeTotal);
			}

			EmploymentSalaryDetail employmentSalaryDetail = new EmploymentSalaryDetail();
			employmentSalaryDetail.setEmploymentPayType(employmentPayType);
			employmentSalaryDetail
					.setEmploymentSalaryHeader(employmentSalaryHeader);
			employmentSalaryDetail.setPayTypeAmount(amount);
			employmentSalaryDetail.setPayTypeName(employmentPayType
					.getPayType().getPayType());

			this.employmentSalaryDetailService.save(employmentSalaryDetail);
		}

		for (EmploymentPayReduction employmentPayReduction : employmentPayReductions) {
			EmploymentSalaryDetail employmentSalaryDetail = new EmploymentSalaryDetail();
			employmentSalaryDetail
					.setEmploymentPayReduction(employmentPayReduction);
			employmentSalaryDetail
					.setEmploymentSalaryHeader(employmentSalaryHeader);
			employmentSalaryDetail.setPayReductionAmount(employmentPayReduction
					.getAmount());
			employmentSalaryDetail.setPayReductionName(employmentPayReduction
					.getPayReduction().getPayReduction());

			this.employmentSalaryDetailService.save(employmentSalaryDetail);
		}

		for (EmploymentPayReduction employmentPayReduction : percentageEmploymentPayReductions) {
			BigDecimal amount = new BigDecimal(0);
			if (employmentPayReduction.getSalaryType().equals(
					SalaryType.BasicSalary)) {
				amount = employmentPayReduction.getAmount()
						.divide(new BigDecimal(100))
						.multiply(basicSalary.getAmount());
			} else if (employmentPayReduction.getSalaryType().equals(
					SalaryType.GrossSalary)) {
				amount = employmentPayReduction.getAmount()
						.divide(new BigDecimal(100)).multiply(payTypeTotal);
			}

			EmploymentSalaryDetail employmentSalaryDetail = new EmploymentSalaryDetail();
			employmentSalaryDetail
					.setEmploymentPayReduction(employmentPayReduction);
			employmentSalaryDetail
					.setEmploymentSalaryHeader(employmentSalaryHeader);
			employmentSalaryDetail.setPayReductionAmount(amount);
			employmentSalaryDetail.setPayReductionName(employmentPayReduction
					.getPayReduction().getPayReduction());

			this.employmentSalaryDetailService.save(employmentSalaryDetail);
		}

		return employmentSalaryHeader;
	}

	public List<EmploymentSalaryHeader> createEmploymentSalaryHeader(
			PayrollSelectEmployeesForm payrollSelectEmployeesForm) {
		List<EmploymentSalaryHeader> employmentSalaryHeaders = new ArrayList<EmploymentSalaryHeader>();
		for (Long employmentId : payrollSelectEmployeesForm.getEmploymentIds()) {
			EmploymentSalaryHeader employmentSalaryHeader = this
					.createEmploymentSalaryHeader(
							payrollSelectEmployeesForm.getPayrollId(),
							payrollSelectEmployeesForm.getPayScheduleId(),
							payrollSelectEmployeesForm.getFromDate(),
							payrollSelectEmployeesForm.getToDate(),
							employmentId);
			employmentSalaryHeaders.add(employmentSalaryHeader);
		}
		return employmentSalaryHeaders;
	}
}
