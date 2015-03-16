package com.suwadi.web.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.suwadi.domain.Designation;
import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.domain.Gender;
import com.suwadi.domain.Role;
import com.suwadi.domain.User;
import com.suwadi.service.DesignationService;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.service.RoleService;
import com.suwadi.service.UserService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.propertyEditor.GenderPropertyEditor;
import com.suwadi.web.validator.UserValidator;

@Controller
@SessionAttributes("user")
@RequestMapping(value = "/admin/users")
public class UsersController {
	private UserService userService;
	private RoleService roleService;
	private DistrictService districtService;
	private UserValidator userValidator;
	private DesignationService designationService;
	private DivisionService divisionService;

	@Autowired
	public UsersController(UserService userService, RoleService roleService,
			DistrictService districtService, UserValidator userValidator,
			DesignationService designationService,
			DivisionService divisionService) {
		this.userService = userService;
		this.roleService = roleService;
		this.districtService = districtService;
		this.userValidator = userValidator;
		this.designationService = designationService;
		this.divisionService = divisionService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));

		binder.registerCustomEditor(Gender.class, new GenderPropertyEditor());
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute(
				"pager",
				this.userService.paginate(pager, new String[] { "roles",
						"profile" }));
		return "admin/users/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<Role> roles = this.roleService.findAll();
		List<Designation> designations = this.designationService.findAll();

		User user = new User();

		model.addAttribute("roles", roles);
		model.addAttribute("designations", designations);
		model.addAttribute("user", user);

		return "admin/users/add";
	}

	@RequestMapping(value = "processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status, Model model) {
		this.userValidator.validate(user, result);
		if (result.hasErrors()) {
			List<Role> roles = this.roleService.findAll();
			List<Designation> designations = this.designationService.findAll();

			model.addAttribute("roles", roles);
			model.addAttribute("designations", designations);

			user.getRoles().removeAll(Collections.singleton(null));
			model.addAttribute("user", user);

			return "admin/users/add";
		} else {
			userService.save(user);
			status.setComplete();
			return "redirect:/admin/users/list";
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		User user = this.userService.findById(id);

		List<Role> roles = this.roleService.findAll();
		List<Designation> designations = this.designationService.findAll();
		List<Division> divisions = this.divisionService
				.findAllByDistrictId(user.getProfile().getDistrict().getId());
		List<District> districts = this.districtService.findAll();

		model.addAttribute("roles", roles);
		model.addAttribute("designations", designations);
		model.addAttribute("districts", districts);
		model.addAttribute("divisions", divisions);
		model.addAttribute("user", user);

		return "admin/users/edit";
	}

	@RequestMapping(value = "/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status, Model model) {
		this.userValidator.validate(user, result);
		if (result.hasErrors()) {
			List<Role> roles = this.roleService.findAll();
			List<Designation> designations = this.designationService.findAll();
			List<Division> divisions = this.divisionService
					.findAllByDistrictId(user.getProfile().getDistrict()
							.getId());
			List<District> districts = this.districtService.findAll();

			model.addAttribute("roles", roles);
			model.addAttribute("designations", designations);
			model.addAttribute("divisions", divisions);
			model.addAttribute("districts", districts);

			user.getRoles().removeAll(Collections.singleton(null));

			model.addAttribute("user", user);

			return "admin/users/edit";
		} else {
			userService.update(user);
			status.setComplete();
			return "redirect:/admin/users/list";
		}
	}
}
