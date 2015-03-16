package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.Role;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface RoleService {
	public Role save(Role role);

	public Role update(Role role);

	public Role findById(Long id);

	public Role delete(Role role);

	public List<Role> findAll();

	public List<Role> findAll(Pager pager);

	public PagedResultSet paginate(Pager pager);

	public Integer getAllCount();
}
