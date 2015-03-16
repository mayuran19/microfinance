package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.GroupService;

public class GroupPropertyEditor extends PropertyEditorSupport {
	private GroupService groupService;

	public GroupPropertyEditor(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.groupService.findById(id));
		} else {
			setValue(null);
		}
	}

}
