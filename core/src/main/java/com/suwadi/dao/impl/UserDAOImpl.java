package com.suwadi.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.UserDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.User;

@Repository("userDAO")
public class UserDAOImpl extends GenericHibernateDAOSupport<User> implements
		UserDAO {
	public UserDAOImpl() {
		super(User.class);
	}

	public User findByUserName(String userName) {
		String hql = String.format(
				"select obj from %s obj where obj.userName = ?",
				User.class.getName());
		Query query = this.getSession().createQuery(hql);
		query.setString(0, userName);
		User user = (User) query.uniqueResult();
		return user;
	}
}
