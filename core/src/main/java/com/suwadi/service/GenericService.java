package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.DomainObject;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface GenericService<T extends DomainObject> {
	public T save(T t);

	public T update(T t);

	public T findById(Long id);

	public T findById(Long id, String... include);

	public T delete(T t);

	public List<T> findAll();

	public List<T> findAll(Pager pager);

	public PagedResultSet paginate(Pager pager);

	public Integer getAllCount();

	public Boolean isUnique(Long id, String fieldName, String fieldValue);

}
