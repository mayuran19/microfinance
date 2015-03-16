package com.suwadi.dao.common;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.suwadi.dao.GenericDAO;
import com.suwadi.domain.DomainObject;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@SuppressWarnings({ "unchecked" })
public class GenericHibernateDAOSupport<T extends DomainObject> extends
		HibernateDaoSupport implements GenericDAO<T> {

	private Class<T> type;

	public GenericHibernateDAOSupport(Class<T> type) {
		this.type = type;
	}

	@Autowired
	public void setSuwadiSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	public T save(T t) {
		t.setCreatedAt(new Date());
		t.setUpdatedAt(new Date());
		getHibernateTemplate().save(t);
		return t;
	}

	public T update(T t) {
		t.setUpdatedAt(new Date());
		getHibernateTemplate().update(t);
		return t;
	}

	public T delete(T t) {
		getHibernateTemplate().delete(t);
		return t;
	}

	public List<T> findAll() {
		String hqlQuery = String.format("select obj from %s obj",
				type.getName());
		return getHibernateTemplate().find(hqlQuery);
	}

	public List<T> findAll(final Pager pager) {
		List<T> obj = this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						String basicQuery = String.format(
								"select obj from %s obj ", type.getName());
						sb.append(basicQuery);
						if (pager.getSortOrder() == "asc") {
							sb.append("order by").append(" ")
									.append(pager.getSortColumn()).append(" ")
									.append("asc").append(" ");
						} else {
							sb.append("order by").append(" ")
									.append(pager.getSortColumn()).append(" ")
									.append("desc").append(" ");
						}
						Query query = session
								.createQuery(sb.toString())
								.setFirstResult(
										(pager.getPage() - 1)
												* pager.getPageSize())
								.setMaxResults(pager.getPageSize());
						return query.list();
					}
				});
		return obj;
	}

	public List<T> findAll(final Pager pager, final String[] joins) {
		List<T> list = this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						String basicQuery = String.format(
								"select obj from %s obj ", type.getName());
						sb.append(basicQuery);
						if (joins != null) {
							for (String join : joins) {
								sb.append("left join fetch obj." + join + " ");
							}
						}
						if (pager.getSortOrder() == "asc") {
							sb.append("order by").append(" ")
									.append("obj." + pager.getSortColumn())
									.append(" ").append("asc").append(" ");
						} else {
							sb.append("order by").append(" ")
									.append("obj." + pager.getSortColumn())
									.append(" ").append("desc").append(" ");
						}
						Query query = session
								.createQuery(sb.toString())
								.setFirstResult(
										(pager.getPage() - 1)
												* pager.getPageSize())
								.setMaxResults(pager.getPageSize());
						return query.list();
					}
				});
		return list;
	}

	public PagedResultSet paginate(Pager pager) {
		List<T> pagedResultSet = this.findAll(pager);
		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(pagedResultSet);
		pr.setRowCount(this.getAllCount());
		return pr;
	}

	public PagedResultSet paginate(Pager pager, String[] joins) {
		List<T> pagedResultSet = this.findAll(pager, joins);
		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(pagedResultSet);
		pr.setRowCount(this.getAllCount());
		return pr;
	}

	public Integer getAllCount() {
		Integer count = this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hqlQuery = String.format(
								"select count(obj.id) from %s obj",
								type.getName());
						Long cnt = (Long) session.createQuery(hqlQuery)
								.uniqueResult();
						int count = cnt.intValue();
						return count;
					}
				});
		return count;
	}

	public Boolean isUnique(final Long id, final String fieldName,
			final String fieldValue) {
		Boolean isUniqu = this.getHibernateTemplate().execute(
				new HibernateCallback<Boolean>() {

					public Boolean doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuilder sb = new StringBuilder();
						sb.append("select count(*) from ");
						sb.append(type.getName());
						sb.append(" obj ");
						sb.append("where obj.");
						sb.append(fieldName);
						sb.append(" = :");
						sb.append(fieldName);
						if (id != null) {
							sb.append(" and ");
							sb.append("obj.id <> :id");
						}
						int count = 0;
						if (id != null) {
							Long longCount = (Long) session
									.createQuery(sb.toString())
									.setParameter("id", id)
									.setParameter(fieldName, fieldValue)
									.uniqueResult();
							count = longCount.intValue();
						} else {
							Long longCount = (Long) session
									.createQuery(sb.toString())
									.setParameter(fieldName, fieldValue)
									.uniqueResult();
							count = longCount.intValue();
						}
						if (count == 0) {
							return true;
						} else {
							return false;
						}
					}
				});
		return isUniqu;
	}

	public T findById(Long id) {
		return this.getHibernateTemplate().get(type, id);
	}

	public T findById(final Long id, final String... include) {
		final String typeName = this.type.getName();
		System.out.println(typeName);
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				String query = String.format("select obj from %s as obj",
						typeName);
				if (include.length > 0) {
					for (int i = 0; i < include.length; i++) {
						query += " left join fetch obj." + include[i];
					}
				}
				query += " where obj.id = :id";

				Query query2 = session.createQuery(query).setLong("id", id);
				return (T) query2.uniqueResult();
			}
		});

	}

	public T merge(T t) {
		return this.getHibernateTemplate().merge(t);
	}

	public List<T> findAll(final String... columnNames) {
		final String typeName = this.type.getName();
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						String selectQuery = "";
						for (int i = 0; i < columnNames.length; i++) {
							if (i == columnNames.length - 1) {
								selectQuery += columnNames[i];
							} else {
								selectQuery += columnNames[i] + ",";
							}
						}
						String hqlQuery = String.format("select %s from %s",
								selectQuery, typeName);
						Query query = session.createQuery(hqlQuery);
						return query.list();
					}
				});

	}

	public List<T> findByProperty(final String propertyName,
			final Object propertyValue, final String orderByColumn,
			final String order, final Integer page, final Integer pageSize) {
		final String typeName = this.type.getName();
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						sb.append("select obj from " + type.getName()
								+ " obj where obj." + propertyName + " = :"
								+ propertyName.replace(".", "_"));
						if (orderByColumn != null && order != null) {
							sb.append(" order by obj." + orderByColumn + " "
									+ order);
						}
						if (page == null || pageSize == null) {
							return session
									.createQuery(sb.toString())
									.setParameter(
											propertyName.replace(".", "_")
													.toString(), propertyValue)
									.list();
						} else {
							return session
									.createQuery(sb.toString())
									.setParameter(
											propertyName.replace(".", "_")
													.toString(), propertyValue)
									.setFirstResult((page - 1) * pageSize)
									.setMaxResults(pageSize).list();
						}
					}
				});

	}

	public List<T> findByProperty(final String propertyName,
			final Object propertyValue) {
		return this.findByProperty(propertyName, propertyValue, null, null,
				null, null);
	}

	public List<T> findByPropertyBetween(final String propertyName,
			final Object[] propertyValues, final String orderByColumn,
			final String order, final Integer page, final Integer pageSize) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						sb.append("select obj from "
								+ type.getName()
								+ " obj where obj."
								+ propertyName
								+ " between :firstCondition and :secondCondition");
						if (orderByColumn != null && order != null) {
							sb.append(" order by obj." + orderByColumn + " "
									+ order);
						}
						if (page == null || pageSize == null) {
							return session
									.createQuery(sb.toString())
									.setParameter("firstCondition",
											propertyValues[0])
									.setParameter("secondCondition",
											propertyValues[1]).list();
						} else {
							return session
									.createQuery(sb.toString())
									.setParameter("firstCondition",
											propertyValues[0])
									.setParameter("secondCondition",
											propertyValues[1])
									.setFirstResult((page - 1) * pageSize)
									.setMaxResults(pageSize).list();
						}
					}
				});

	}

	public List<T> findByPropertyBetween(String propertyName,
			Object[] propertyValues) {
		return this.findByPropertyBetween(propertyName, propertyValues, null,
				null, null, null);
	}

	public List<T> findByPropertyIn(final String propertyName,
			final Object[] propertyValues, final String orderByColumn,
			final String order, final Integer page, final Integer pageSize) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						sb.append("select obj from " + type.getName()
								+ " obj where obj." + propertyName
								+ " in(:propertyValues)");
						if (orderByColumn != null && order != null) {
							sb.append(" order by obj." + orderByColumn + " "
									+ order);
						}
						if (page == null || pageSize == null) {
							return session
									.createQuery(sb.toString())
									.setParameterList("propertyValues",
											propertyValues).list();
						} else {
							return session
									.createQuery(sb.toString())
									.setParameterList("propertyValues",
											propertyValues)
									.setFirstResult((page - 1) * pageSize)
									.setMaxResults(pageSize).list();
						}
					}
				});

	}

	public List<T> findByPropertyIn(String propertyName, Object[] propertyValues) {
		return this.findByPropertyIn(propertyName, propertyValues, null, null,
				null, null);
	}

	public List<T> findByProperties(final String[] propertyNames,
			final Object[] propertyValues, final String orderByColumn,
			final String order, final Integer page, final Integer pageSize) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						sb.append("select obj from " + type.getName()
								+ " obj where ");
						for (int i = 0; i < propertyNames.length; i++) {
							if (i + 1 == propertyNames.length) {
								// the last condition
								sb.append(" obj." + propertyNames[i] + " = :"
										+ propertyNames[i]);
							} else {
								sb.append(" obj." + propertyNames[i] + " = :"
										+ propertyNames[i] + " and ");
							}
						}
						if (orderByColumn != null && order != null) {
							sb.append(" order by obj." + orderByColumn + " "
									+ order);
						}
						Query query = session.createQuery(sb.toString());
						for (int i = 0; i < propertyNames.length; i++) {
							query.setParameter(propertyNames[i].toString(),
									propertyValues[i]);
						}
						if (page == null || pageSize == null) {
							return query.list();
						} else {
							return query.setFirstResult((page - 1) * pageSize)
									.setMaxResults(pageSize).list();
						}
					}
				});
	}

	public List<T> findByProperties(String[] propertyNames,
			Object[] propertyValues) {
		return this.findByProperties(propertyNames, propertyValues, null, null,
				null, null);
	}

	public T findFirstByProperties(final String[] propertyNames,
			final Object[] propertyValues) {
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer sb = new StringBuffer();
				sb.append("select obj from " + type.getName() + " obj where ");
				for (int i = 0; i < propertyNames.length; i++) {
					if (i + 1 == propertyNames.length) {
						// the last condition
						sb.append(" obj." + propertyNames[i] + " = :"
								+ propertyNames[i]);
					} else {
						sb.append(" obj." + propertyNames[i] + " = :"
								+ propertyNames[i] + " and ");
					}
				}
				Query query = session.createQuery(sb.toString());
				for (int i = 0; i < propertyNames.length; i++) {
					query.setParameter(propertyNames[i].toString(),
							propertyValues[i]);
				}
				return (T) query.uniqueResult();
			}
		});

	}

	public T findFirstByProperty(String propertyName, Object propertyValue) {
		return this.findFirstByProperties(new String[] { propertyName },
				new Object[] { propertyValue });
	}

	public List<T> findByPropertiesWithEagerFetch(final String[] propertyNames,
			final Object[] propertyValues, final String[] joins,
			final String orderByColumn, final String order, final Integer page,
			final Integer pageSize) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						sb.append("select obj from " + type.getName() + " obj ");
						if (joins != null) {
							for (String join : joins) {
								sb.append(" left join fetch obj." + join + " ");
							}
						}

						sb.append(" where ");
						for (int i = 0; i < propertyNames.length; i++) {
							if (i + 1 == propertyNames.length) {
								// the last condition
								sb.append(" obj." + propertyNames[i] + " = :"
										+ propertyNames[i]);
							} else {
								sb.append(" obj." + propertyNames[i] + " = :"
										+ propertyNames[i] + " and ");
							}
						}
						if (orderByColumn != null && order != null) {
							sb.append(" order by obj." + orderByColumn + " "
									+ order);
						}
						Query query = session.createQuery(sb.toString());
						for (int i = 0; i < propertyNames.length; i++) {
							query.setParameter(propertyNames[i].toString(),
									propertyValues[i]);
						}
						if (page == null || pageSize == null) {
							return query.list();
						} else {
							return query.setFirstResult((page - 1) * pageSize)
									.setMaxResults(pageSize).list();
						}
					}
				});

	}

	public List<T> findByPropertiesWithEagerFetch(String[] propertyNames,
			Object[] propertyValues, String[] joins) {
		return this.findByPropertiesWithEagerFetch(propertyNames,
				propertyValues, joins, null, null, null, null);
	}

	public List<T> findByPropertyWithEagerFetch(final String propertyName,
			final Object propertyValue, final String[] joins,
			final String orderByColumn, final String order, final Integer page,
			final Integer pageSize) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer();
						sb.append("select obj from " + type.getName() + " obj ");
						if (joins != null) {
							for (String join : joins) {
								sb.append(" left join fetch obj." + join + " ");
							}
						}
						sb.append(" where obj." + propertyName + " = :"
								+ replaceDotWithUnderscore(propertyName));
						if (orderByColumn != null && order != null) {
							sb.append(" order by obj." + orderByColumn + " "
									+ order);
						}
						if (page == null || pageSize == null) {
							return session
									.createQuery(sb.toString())
									.setParameter(
											replaceDotWithUnderscore(propertyName),
											propertyValue).list();
						} else {
							return session
									.createQuery(sb.toString())
									.setParameter(propertyName.toString(),
											propertyValue)
									.setFirstResult((page - 1) * pageSize)
									.setMaxResults(pageSize).list();
						}
					}
				});

	}

	public List<T> findByPropertyWithEagerFetch(String propertyName,
			Object propertyValue, String[] joins) {
		return this.findByPropertyWithEagerFetch(propertyName, propertyValue,
				joins, null, null, null, null);
	}

	public T findFirstByPropertiesWithEagerFetch(final String[] propertyNames,
			final Object[] propertyValues, final String[] joins) {
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer sb = new StringBuffer();
				sb.append("select obj from " + type.getName() + " obj ");
				if (joins != null) {
					for (String join : joins) {
						sb.append(" left join fetch obj." + join + " ");
					}
				}
				sb.append(" where ");
				for (int i = 0; i < propertyNames.length; i++) {
					if (i + 1 == propertyNames.length) {
						// the last condition
						sb.append(" obj." + propertyNames[i] + " = :"
								+ propertyNames[i]);
					} else {
						sb.append(" obj." + propertyNames[i] + " = :"
								+ propertyNames[i] + " and ");
					}
				}
				Query query = session.createQuery(sb.toString());
				for (int i = 0; i < propertyNames.length; i++) {
					query.setParameter(propertyNames[i].toString(),
							propertyValues[i]);
				}
				return (T) query.uniqueResult();
			}
		});

	}

	public T findFirstByPropertyWithEagerFetch(String propertyName,
			Object propertyValue, String[] joins) {
		return this.findFirstByPropertiesWithEagerFetch(
				new String[] { propertyName }, new Object[] { propertyValue },
				joins);
	}

	private String replaceDotWithUnderscore(String str) {
		return str.replace(".", "_");
	}

	public List<T> findAllWithEagerFetch(final String[] joins) {
		final String typeName = this.type.getName();
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						String query = String.format(
								"select obj from %s as obj", typeName);
						if (joins.length > 0) {
							for (int i = 0; i < joins.length; i++) {
								query += " left join fetch obj." + joins[i];
							}
						}

						Query query2 = session.createQuery(query);
						return query2.list();
					}
				});
	}

	public T refresh(final T t) {
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				session.refresh(t);
				return t;
			}
		});
	}

}