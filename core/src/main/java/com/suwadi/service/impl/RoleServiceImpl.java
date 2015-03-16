package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.RoleDAO;
import com.suwadi.domain.Role;
import com.suwadi.service.RoleService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public Role findById(Long id) {
		return this.getRoleDAO().findById(id);
	}

	public Role delete(Role role) {
		return this.getRoleDAO().delete(role);
	}

	public List<Role> findAll() {
		return this.roleDAO.findAll();
	}

	public List<Role> findAll(Pager pager) {
		return this.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.roleDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.roleDAO.getAllCount();
	}

	public Role save(Role role) {
		return this.roleDAO.save(role);
	}

	public Role update(Role role) {
		return this.roleDAO.update(role);
	}

}
