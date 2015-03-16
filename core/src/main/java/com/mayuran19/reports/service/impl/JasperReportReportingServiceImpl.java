package com.mayuran19.reports.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.CookieSetting;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.util.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuran19.common.constant.IConstant;
import com.mayuran19.reports.jasperServer.runReport.request.ObjectFactory;
import com.mayuran19.reports.jasperServer.runReport.request.ResourceDescriptor;
import com.mayuran19.reports.jasperServer.runReport.response.Report;
import com.mayuran19.reports.service.JasperServerReportingService;
import com.suwadi.service.ConfigurationService;

@Service("jasperServerReportingService")
public class JasperReportReportingServiceImpl implements
		JasperServerReportingService {
	private static final Logger LOGGER = Logger
			.getLogger(JasperReportReportingServiceImpl.class);
	private ConfigurationService configurationService;
	Series<CookieSetting> sessionCookies = null;

	public Series<CookieSetting> getSessionCookies() {
		return sessionCookies;
	}

	public void setSessionCookies(Series<CookieSetting> sessionCookies) {
		this.sessionCookies = sessionCookies;
	}

	@Autowired
	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public InputStream downloadReport(String reportName, String outputFormat) {
		LOGGER.debug("downloadReport");
		InputStream is = null;
		Report report = this.runReport(reportName, outputFormat);
		Client client = new Client(Protocol.HTTP);
		Request request = new Request();
		for (CookieSetting cookieSetting : sessionCookies) {
			request.getCookies().add(cookieSetting);
		}
		request.setResourceRef(this.getGeneratedReportDownloadURL(report));
		request.setMethod(Method.GET);
		Response response = client.handle(request);
		try {
			is = response.getEntity().getStream();
		} catch (IOException e) {
			LOGGER.error("Exception", e);
		}

		return is;
	}

	public Report runReport(String reportName, String outputFormat) {
		String reportURI = "/reports/" + reportName;
		Client client = new Client(Protocol.HTTP);
		Request request = new Request();
		if (this.sessionCookies == null) {
			LOGGER.info("No session found, Loggin in to Jasper Server");
			this.login();
		}
		for (CookieSetting cookieSetting : sessionCookies) {
			request.getCookies().add(cookieSetting);
		}
		request.setResourceRef(this.getReportURLByReportName(reportName,
				outputFormat));
		request.setMethod(Method.PUT);
		Representation representation = new StringRepresentation(
				this.getRunReportResourceDiscriptorXML(reportName, reportURI),
				MediaType.TEXT_XML);
		request.setEntity(representation);
		Response response = client.handle(request);
		Report report = this.getJasperReportRunResponse(response.getEntity());

		return report;
	}

	/**
	 * this method connect to Jasper Report Server and set the sessionCookies
	 */
	public void login() {
		Client client = new Client(Protocol.HTTP);
		Request request = new Request();
		request.setResourceRef(this.configurationService
				.findValueByKey(IConstant.ConfigurationKey.JASPERSERVER_LOGIN_URL));
		request.setMethod(Method.POST);
		Form form = new Form();
		form.set(
				this.configurationService
						.findValueByKey(IConstant.ConfigurationKey.JASPERSERVER_LOGIN_USERNAME_KEY),
				this.configurationService
						.findValueByKey(IConstant.ConfigurationKey.JASPERSERVER_LOGIN_USERNAME_VALUE));
		form.set(
				this.configurationService
						.findValueByKey(IConstant.ConfigurationKey.JASPERSERVER_LOGIN_PASSWORD_KEY),
				this.configurationService
						.findValueByKey(IConstant.ConfigurationKey.JASPERSERVER_LOGIN_PASSWORD_VALUE));
		Representation representation = form.getWebRepresentation();
		request.setEntity(representation);
		Response response = client.handle(request);
		this.sessionCookies = response.getCookieSettings();
	}

	public String getReportURLByReportName(String reportName,
			String outputFormat) {
		String str = this.configurationService
				.findValueByKey(IConstant.ConfigurationKey.JASPERSERVER_HOME_URL)
				+ "rest/report/"
				+ reportName
				+ "?RUN_OUTPUT_FORMAT="
				+ outputFormat;
		return str;
	}

	public String getRunReportResourceDiscriptorXML(String reportName,
			String reportURI) {
		String xml = null;
		ObjectFactory factory = new ObjectFactory();
		ResourceDescriptor resourceDescriptor = factory
				.createResourceDescriptor();
		resourceDescriptor.setIsNew("false");
		resourceDescriptor.setName(reportName);
		resourceDescriptor.setUriString(reportURI);
		resourceDescriptor.setWsType("reportUnit");
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(ResourceDescriptor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(resourceDescriptor, stringWriter);
			xml = stringWriter.toString();
		} catch (JAXBException e) {
			LOGGER.error("Exception", e);
		}

		return xml;
	}

	public Report getJasperReportRunResponse(Representation representation) {
		Report report = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Report.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			report = (Report) unmarshaller
					.unmarshal(representation.getReader());
		} catch (IOException e) {
			LOGGER.error(
					"Exception occured while getting runReport resulting xml",
					e);
		} catch (JAXBException e) {
			LOGGER.error("Exception occured while getting jaxbContext", e);
		}
		return report;
	}

	public String getGeneratedReportDownloadURL(Report report) {
		String str = this.configurationService
				.findValueByKey(IConstant.ConfigurationKey.JASPERSERVER_HOME_URL)
				+ "rest/report/" + report.getUuid() + "?file=report";
		return str;
	}
}
