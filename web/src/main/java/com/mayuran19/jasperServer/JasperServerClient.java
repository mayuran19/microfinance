package com.mayuran19.jasperServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
import org.springframework.stereotype.Component;

import com.mayuran19.common.constant.IConstant;
import com.mayuran19.reports.jasperServer.runReport.response.Report;
import com.suwadi.service.ConfigurationService;

@Component("jasperServerClient")
public class JasperServerClient {
	private static final Logger LOGGER = Logger
			.getLogger(JasperServerClient.class);
	private ConfigurationService configurationService;
	Series<CookieSetting> sessionCookies = null;

	@Autowired
	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void runReport(String reportUrl) {
		Client client = new Client(Protocol.HTTP);
		Request request = new Request();
		if (this.sessionCookies == null) {
			sessionCookies = this.login();
		}
		this.sessionCookies = this.login();
		for (CookieSetting cookieSetting : sessionCookies) {
			request.getCookies().add(cookieSetting);
		}

		request.setResourceRef(reportUrl);
		request.setMethod(Method.PUT);
		Representation representation = new StringRepresentation(
				"<resourceDescriptor name=\"ExpenseReport\" wsType=\"reportUnit\" uriString=\"/reports/ExpenseReport\" isNew=\"false\"></resourceDescriptor>",
				MediaType.TEXT_XML);
		request.setEntity(representation);

		Response response = client.handle(request);
		Series<CookieSetting> cookies = response.getCookieSettings();
		sessionCookies.addAll(cookies);

		Report report = this.getJasperReportRunResponse(response.getEntity());
		this.downloadReport("http://localhost:8080/jasperserver/rest/report/"
				+ report.getUuid() + "?file=report");
	}

	public Series<CookieSetting> login() {
		Series<CookieSetting> cookieSettings = null;
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
		cookieSettings = response.getCookieSettings();

		return cookieSettings;
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

	public void downloadReport(String downloadURL) {
		Client client = new Client(Protocol.HTTP);
		Request request = new Request();
		for (CookieSetting cookieSetting : sessionCookies) {
			request.getCookies().add(cookieSetting);
		}
		request.setResourceRef(downloadURL);
		request.setMethod(Method.GET);
		Response response = client.handle(request);
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			is = response.getEntity().getStream();
			fos = new FileOutputStream("test.pdf");
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(response);
	}
}
