package com.suwadi.web.bindingInitializer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.suwadi.domain.Designation;
import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.service.DesignationService;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.service.RoleService;
import com.suwadi.web.propertyEditor.DesignationPropertyEditor;
import com.suwadi.web.propertyEditor.DivisionPropertyEditor;
import com.suwadi.web.propertyEditor.UserDistrictPropertyEditor;
import com.suwadi.web.propertyEditor.UserPropertyEditor;

public class UserBindingInitializer implements WebBindingInitializer {
	private RoleService roleService;
	private DistrictService districtService;
	private DivisionService divisionService;
	private DesignationService designationService;

	@Autowired
	public UserBindingInitializer(RoleService roleService,
			DistrictService districtService, DivisionService divisionService,
			DesignationService designationService) {
		this.roleService = roleService;
		this.districtService = districtService;
		this.divisionService = divisionService;
		this.designationService = designationService;
	}

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(List.class, "roles",
				new UserPropertyEditor(List.class, roleService));
		binder.registerCustomEditor(District.class,
				new UserDistrictPropertyEditor(districtService));
		binder.registerCustomEditor(Division.class, new DivisionPropertyEditor(
				divisionService));
		binder.registerCustomEditor(Designation.class,
				new DesignationPropertyEditor(designationService));
	}
}
