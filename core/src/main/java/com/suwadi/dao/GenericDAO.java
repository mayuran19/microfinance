package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.DomainObject;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface GenericDAO<T extends DomainObject> {
	public T save(T t);

	public T update(T t);

	public T delete(T t);

	public T findById(Long id);

	public T findById(Long id, String... include);

	public List<T> findAll();

	public List<T> findAll(Pager pager);

	public PagedResultSet paginate(Pager pager);

	public Integer getAllCount();

	public Boolean isUnique(Long id, String fieldName, String fieldValue);

	public T merge(T t);

	public List<T> findAll(String... columnNames);

	public List<T> findByProperty(String propertyName, Object propertyValue,
			String orderByColumn, String order, Integer page, Integer pageSize);

	public List<T> findByProperty(String propertyName, Object propertyValue);

	public List<T> findByPropertyBetween(String propertyName,
			Object[] propertyValues, String orderByColumn, String order,
			Integer page, Integer pageSize);

	public List<T> findByPropertyBetween(String propertyName,
			Object[] propertyValues);

	public List<T> findByPropertyIn(String propertyName,
			Object[] propertyValues, String orderByColumn, String order,
			Integer page, Integer pageSize);

	public List<T> findByPropertyIn(String propertyName, Object[] propertyValues);

	public List<T> findByProperties(String[] propertyNames,
			Object[] propertyValues, String orderByColumn, String order,
			Integer page, Integer pageSize);

	public List<T> findByProperties(String[] propertyNames,
			Object[] propertyValues);

	public T findFirstByProperties(String[] propertyNames,
			Object[] propertyValues);

	public T findFirstByProperty(String propertyName, Object propertyValue);

	public List<T> findByPropertiesWithEagerFetch(String[] propertyNames,
			Object[] propertyValues, String[] joins, String orderByColumn,
			String order, Integer page, Integer pageSize);

	public List<T> findByPropertiesWithEagerFetch(String[] propertyNames,
			Object[] propertyValues, String[] joins);

	public T findFirstByPropertiesWithEagerFetch(String[] propertyNames,
			Object[] propertyValues, String[] joins);

	public T findFirstByPropertyWithEagerFetch(String propertyName,
			Object propertyValue, String[] joins);

	public List<T> findByPropertyWithEagerFetch(String propertyName,
			Object propertyValue, String[] joins, String orderByColumn,
			String order, Integer page, Integer pageSize);

	public List<T> findByPropertyWithEagerFetch(String propertyName,
			Object propertyValue, String[] joins);

	public List<T> findAllWithEagerFetch(String[] joins);

	public List<T> findAll(Pager pager, String[] joins);

	public PagedResultSet paginate(Pager pager, String[] joins);

}
