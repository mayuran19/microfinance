package com.mayuran19.reports.jasperReport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("jasperReportHelper")
public class JasperReportHelper {
	private static final Logger LOGGER = Logger
			.getLogger(JasperReportHelper.class);
	private DataSource dataSource = null;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ByteArrayInputStream printReport(String reportJrxmlFile,
			String format) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			File file = new File(reportJrxmlFile);
			InputStream in = JasperReportHelper.class
					.getResourceAsStream(reportJrxmlFile);
			System.out.println(file.getAbsolutePath());
			JasperDesign jasperDesign = JRXmlLoader.load(in);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			Map<String, Object> parameters = new HashMap<String, Object>();
			Connection connection = this.dataSource.getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, connection);

			if (format.equals("PDF")) {
				JasperExportManager.exportReportToPdfStream(jasperPrint,
						outputStream);
			} else if (format.equals("XLS")) {
				JRXlsExporter jrXlsExporter = new JRXlsExporter();
				jrXlsExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,
						jasperPrint);
				jrXlsExporter.setParameter(
						JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
				jrXlsExporter
						.setParameter(
								JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
								Boolean.TRUE);
				jrXlsExporter.exportReport();
			}

		} catch (JRException e) {
			LOGGER.error("Exception", e);
		} catch (SQLException e) {
			LOGGER.error("Exception", e);
		}

		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
