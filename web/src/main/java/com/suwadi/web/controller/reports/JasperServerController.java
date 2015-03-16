package com.suwadi.web.controller.reports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mayuran19.reports.jasperReport.JasperReportHelper;
import com.mayuran19.reports.service.JasperServerReportingService;

@Controller
public class JasperServerController {
	private static final Logger LOGGER = Logger
			.getLogger(JasperServerController.class);
	private JasperServerReportingService jasperServerReportingService;
	private JasperReportHelper jasperReportHelper;

	@Autowired
	public void setJasperServerReportingService(
			JasperServerReportingService jasperServerReportingService) {
		this.jasperServerReportingService = jasperServerReportingService;
	}

	@Autowired
	public void setJasperReportHelper(JasperReportHelper jasperReportHelper) {
		this.jasperReportHelper = jasperReportHelper;
	}

	@RequestMapping(value = "/reports/expenseReport")
	public void expenseReport(HttpServletRequest request,
			HttpServletResponse response) {
		String type = request.getParameter("reportType") == null ? "XLS"
				: request.getParameter("reportType");
		ByteArrayInputStream is = this.jasperReportHelper.printReport(
				"/reports/jasper/ExpenseReport.jrxml", type);
		response.setContentType("application/msexcel");
		String fileName = type.equals("XLS") ? "expenseReport.xls"
				: "expenseReport.pdf";
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		try {
			OutputStream out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			LOGGER.error("Exception", e);
		}
	}
}
