package com.suwadi.web.propertyEditor;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import com.suwadi.domain.Role;
import com.suwadi.service.RoleService;

public class RolePropertyEditor extends CustomCollectionEditor {
	private RoleService roleService;

	public RolePropertyEditor(Class collectionType, RoleService roleService) {
		super(collectionType);
		this.roleService = roleService;
	}

	@Override
	protected Object convertElement(Object element) {
		if (element instanceof Long) {
			Long id = (Long) element;
			return this.roleService.findById(id);
		} else if (element instanceof Role) {
			return element;
		} else if (element instanceof String && !"".equals(element)) {
			Long id = Long.valueOf(element.toString());
			return this.roleService.findById(id);
		} else {
			return null;
		}
	}

}
