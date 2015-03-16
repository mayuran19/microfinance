package com.suwadi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.ExpenseDetailDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.ExpenseDetail;

@Repository("expenseDetailDAO")
public class ExpenseDetailDAOImpl extends
		GenericHibernateDAOSupport<ExpenseDetail> implements ExpenseDetailDAO {

	public ExpenseDetailDAOImpl() {
		super(ExpenseDetail.class);
	}

	public void save(List<ExpenseDetail> expenseDetails) {
		for (int i = 0; i < expenseDetails.size(); i++) {
			getHibernateTemplate().save(expenseDetails.get(i));
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}

	public void merge(List<ExpenseDetail> expenseDetails) {
		for (int i = 0; i < expenseDetails.size(); i++) {
			getHibernateTemplate().merge(expenseDetails.get(i));
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}

	public void saveOrMerge(List<ExpenseDetail> expenseDetails) {
		List<ExpenseDetail> newList = new ArrayList<ExpenseDetail>();
		List<ExpenseDetail> mergeList = new ArrayList<ExpenseDetail>();
		for (ExpenseDetail expenseDetail : expenseDetails) {
			if (expenseDetail.getId() != null) {
				mergeList.add(expenseDetail);
			} else {
				newList.add(expenseDetail);
			}
		}

		this.save(newList);
		this.merge(mergeList);
	}

	public void delete(List<ExpenseDetail> expenseDetails) {
		int batchSize = 1000;
		int counter = 0;
		while (expenseDetails.size() > counter) {
			if (expenseDetails.size() - counter < 1000) {
				this.deleteInBatch(expenseDetails.subList(counter,
						expenseDetails.size() - counter));
			} else {
				this.deleteInBatch(expenseDetails.subList(counter, 1000));
			}
			counter += batchSize;
		}

	}

	private void deleteInBatch(List<ExpenseDetail> expenseDetails) {
		if (expenseDetails.size() > 0) {
			Long[] idArray = new Long[expenseDetails.size()];
			for (int i = 0; i < expenseDetails.size(); i++) {
				idArray[i] = expenseDetails.get(i).getId();
			}
			String hqlQuery = String.format(
					"delete from %s where id in(:idList)",
					ExpenseDetail.class.getName());
			Query query = this.getSession().createQuery(hqlQuery);
			query.setParameterList("idList", idArray);
			query.executeUpdate();
		}
	}

	public List<ExpenseDetail> findByExpenseId(Long expenseId) {
		String hqlQuery = String.format(
				"select obj from %s obj where obj.expense.id = :expenseId",
				ExpenseDetail.class.getName());
		Query query = this.getSession().createQuery(hqlQuery)
				.setLong("expenseId", expenseId);
		return query.list();
	}

}
