package com.suwadi.web.controller.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportsController {
	@RequestMapping(value = "/reports/index")
	public String index() {
		return "/reports/index";
	}
}
