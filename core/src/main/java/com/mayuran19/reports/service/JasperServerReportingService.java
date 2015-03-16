package com.mayuran19.reports.service;

import java.io.InputStream;

public interface JasperServerReportingService {
	public InputStream downloadReport(String reportName, String outputFormat);
}
