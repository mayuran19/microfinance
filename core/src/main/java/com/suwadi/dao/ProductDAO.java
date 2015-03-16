package com.suwadi.dao;

import com.suwadi.domain.Product;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface ProductDAO extends GenericDAO<Product>{

	PagedResultSet findAllActiveProducts(Pager pager);

}
