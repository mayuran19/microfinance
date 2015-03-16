package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.ProductDAO;
import com.suwadi.domain.Product;
import com.suwadi.service.ProductService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	private ProductDAO productDAO;

	public ProductDAO getProductDAO(){
		return productDAO;
	}
	
	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public Product save(Product t) {
		return this.productDAO.save(t);
	}

	public Product update(Product t) {
		return this.productDAO.update(t);
	}

	public Product findById(Long id) {
		return this.productDAO.findById(id);
	}

	public Product delete(Product t) {
		return this.productDAO.delete(t);
	}

	public List<Product> findAll() {
		return this.productDAO.findAll();
	}

	public List<Product> findAll(Pager pager) {
		return this.productDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.productDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.productDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.productDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet findAllActiveProducts(Pager pager) {
		return this.getProductDAO().findAllActiveProducts(pager);
	}

	public Product findById(Long id, String... include) {
		return this.productDAO.findById(id, include);
	}

}
