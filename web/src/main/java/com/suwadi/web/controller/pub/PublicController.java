package com.suwadi.web.controller.pub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublicController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "public/index";
	}
}
