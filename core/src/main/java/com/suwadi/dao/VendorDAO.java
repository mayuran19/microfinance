package com.suwadi.dao;

import com.suwadi.domain.Vendor;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface VendorDAO extends GenericDAO<Vendor> {

	PagedResultSet findAllActiveVendors(Pager pager);

}
