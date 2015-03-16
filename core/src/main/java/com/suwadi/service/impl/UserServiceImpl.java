package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.UserDAO;
import com.suwadi.domain.User;
import com.suwadi.service.UserService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User findById(Long id) {
		return this.getUserDAO().findById(id);
	}

	public User delete(User user) {
		return this.getUserDAO().delete(user);
	}

	public List<User> findAll() {
		return this.userDAO.findAll();
	}

	public List<User> findAll(Pager pager) {
		return this.userDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.userDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.userDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.userDAO.isUnique(id, fieldName, fieldValue);
	}

	public User save(User user) {
		return this.userDAO.save(user);
	}

	public User update(User user) {
		return this.userDAO.update(user);
	}

	public User findByUserName(String userName) {
		return this.userDAO.findByUserName(userName);
	}

	public User findById(Long id, String... include) {
		return this.userDAO.findById(id, include);
	}

	public PagedResultSet paginate(Pager pager, String[] joins) {
		return this.userDAO.paginate(pager, joins);
	}

}
