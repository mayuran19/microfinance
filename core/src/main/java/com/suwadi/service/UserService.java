package com.suwadi.service;

import com.suwadi.domain.User;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface UserService extends GenericService<User> {
	public User findByUserName(String userName);

	public PagedResultSet paginate(Pager pager, String[] joins);
}
