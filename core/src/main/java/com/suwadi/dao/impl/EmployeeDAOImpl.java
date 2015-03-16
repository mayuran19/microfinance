package com.suwadi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmployeeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Employee;
import com.suwadi.web.model.payroll.EmployeeListDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends GenericHibernateDAOSupport<Employee>
		implements EmployeeDAO {

	public EmployeeDAOImpl() {
		super(Employee.class);
	}

	@SuppressWarnings("unchecked")
	public PagedResultSet findAllEmployees(Pager pager) {
		List<EmployeeListDisplay> employeeListDisplays = new ArrayList<EmployeeListDisplay>();
		Session session = this.getSession();
		String hqlQuery = String
				.format("select employment.id,employee.id,employee.name,employee.mobileNo,employee.nicNo from %s employee left join employee.employments as employment with employment.employmentStatus.id = 1",
						Employee.class.getName());
		Query query = session.createQuery(hqlQuery)
				.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List<Object[]> lst = query.list();
		for (Object[] obj : lst) {
			Long employmentId = (Long) obj[0];
			Long employeeId = (Long) obj[1];
			String name = (String) obj[2];
			String mobileNo = (String) obj[3];
			String nicNo = (String) obj[4];
			EmployeeListDisplay employeeListDisplay = new EmployeeListDisplay();
			employeeListDisplay.setActiveEmploymentId(employmentId);
			employeeListDisplay.setEmployeeId(employeeId);
			employeeListDisplay.setMobileNumber(mobileNo);
			employeeListDisplay.setName(name);
			employeeListDisplay.setNicNo(nicNo);
			employeeListDisplays.add(employeeListDisplay);
		}

		String countQuery = String
				.format("select count(*) from %s employee left join employee.employments as employment with employment.employmentStatus.id = 1",
						Employee.class.getName());
		Long cnt = (Long) session.createQuery(countQuery).uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(employeeListDisplays);
		pr.setRowCount(count);

		session.close();
		return pr;
	}
}
