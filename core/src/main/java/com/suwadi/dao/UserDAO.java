package com.suwadi.dao;

import com.suwadi.domain.User;

public interface UserDAO extends GenericDAO<User> {
	public User findByUserName(String userName);
}
