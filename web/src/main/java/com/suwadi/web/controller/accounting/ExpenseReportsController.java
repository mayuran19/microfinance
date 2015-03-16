package com.suwadi.web.controller.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mayuran19.reports.jasperReport.JasperReportHelper;
import com.suwadi.service.ExpenseService;
import com.suwadi.web.pagination.Pager;

@Controller
public class ExpenseReportsController {
	private ExpenseService expenseService;
	private JasperReportHelper jasperReportHelper;

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Autowired
	public void setJasperReportHelper(JasperReportHelper jasperReportHelper) {
		this.jasperReportHelper = jasperReportHelper;
	}

	@RequestMapping(value = "/accounting/expenseReports/index")
	public String index(Model model, Pager pager) {
		// this.expenseService.getExpenseReportDisplayPaginated(pager);
		// ExpenseReportSearch expenseReportSearch = new ExpenseReportSearch();
		// model.addAttribute("expenseReportSearch", expenseReportSearch);
//		this.jasperReportHelper
//				.printPDFReport("/reports/jasper/ExpenseReport.jrxml");
		return "/accounting/expenseReports/index";
	}
}
