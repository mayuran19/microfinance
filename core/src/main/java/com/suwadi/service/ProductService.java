package com.suwadi.service;

import com.suwadi.domain.Product;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface ProductService extends GenericService<Product>{

	public PagedResultSet findAllActiveProducts(Pager pager);

}
