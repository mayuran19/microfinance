package com.suwadi.service;

import com.suwadi.domain.Vendor;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface VendorService extends GenericService<Vendor> {
	public PagedResultSet findAllActiveVendors(Pager pager);

}
