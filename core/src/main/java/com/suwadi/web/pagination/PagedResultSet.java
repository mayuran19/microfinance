package com.suwadi.web.pagination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagedResultSet {
	private Pager pager;
	int rowCount = 0;
	List resultSet = new ArrayList();
	List<Integer> pages = new ArrayList<Integer>();
	int lastPage;
	int firstPage = 1;
	Object object;

	public int getLastPage() {
		int pageSize = pager.getPageSize();
		double value = (rowCount / pageSize);
		double value1 = ((((rowCount % pageSize) * 10) * 10) / pageSize) * 0.01;
		int lastPage = (int) Math.ceil(value + value1);
		return lastPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List getResultSet() {
		return resultSet;
	}

	public void setResultSet(List resultSet) {
		this.resultSet = resultSet;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<Integer> getPages() {
		List<Integer> lst = new ArrayList<Integer>();
		Integer totalPages = (int) Math.ceil(Double.valueOf(Integer
				.toString(rowCount))
				/ Double.valueOf(Integer.toString(pager.getPageSize())));
		if (totalPages <= 10) {
			for (int i = 0; i < totalPages; i++) {
				lst.add(i + 1);
			}
		} else if (totalPages > 10) {
			Integer[] leftArray = null;
			Integer[] rightArray = null;

			if (pager.getPage() - 6 < 0) {
				leftArray = new Integer[pager.getPage() - 1];
			} else {
				leftArray = new Integer[5];
			}

			if (totalPages - pager.getPage() <= 5) {
				rightArray = new Integer[totalPages - pager.getPage() + 1];
			} else {
				rightArray = new Integer[5];
			}

			// left array
			if (pager.getPage() - 5 <= 0) {
				for (int i = 1; i < pager.getPage(); i++) {
					leftArray[i - 1] = i;
				}
			} else {

				for (int i = 0; i < 5; i++) {
					leftArray[4 - i] = pager.getPage() - (i + 1);

				}
			}

			// right array
			if (totalPages - pager.getPage() <= 5) {
				int tmp = 0;
				for (int i = pager.getPage(); i < totalPages + 1; i++) {
					rightArray[tmp] = i;
					tmp++;
				}
			} else {
				int tmp = 0;
				for (int i = pager.getPage(); i < pager.getPage() + 5; i++) {
					rightArray[tmp] = i;
					tmp++;
				}
			}
			lst.addAll(Arrays.asList(leftArray));
			lst.addAll(Arrays.asList(rightArray));
		}
		return lst;
	}
}