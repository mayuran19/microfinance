package com.suwadi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.IncomeDetailDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.IncomeDetail;

@Repository("IncomeDetailDAO")
public class IncomeDetailDAOImpl extends
		GenericHibernateDAOSupport<IncomeDetail> implements IncomeDetailDAO {

	public IncomeDetailDAOImpl() {
		super(IncomeDetail.class);
	}

	public void save(List<IncomeDetail> incomeDetails) {
		for (int i = 0; i < incomeDetails.size(); i++) {
			getHibernateTemplate().save(incomeDetails.get(i));
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}

	public void merge(List<IncomeDetail> incomeDetails) {
		for (int i = 0; i < incomeDetails.size(); i++) {
			getHibernateTemplate().merge(incomeDetails.get(i));
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}

	public void saveOrMerge(List<IncomeDetail> incomeDetails) {
		List<IncomeDetail> newList = new ArrayList<IncomeDetail>();
		List<IncomeDetail> mergeList = new ArrayList<IncomeDetail>();
		for (IncomeDetail incomeDetail : incomeDetails) {
			if (incomeDetail.getId() != null) {
				mergeList.add(incomeDetail);
			} else {
				newList.add(incomeDetail);
			}
		}

		this.save(newList);
		this.merge(mergeList);
	}

	public void delete(List<IncomeDetail> incomeDetails) {
		int batchSize = 1000;
		int counter = 0;
		while (incomeDetails.size() > counter) {
			if (incomeDetails.size() - counter < 1000) {
				this.deleteInBatch(incomeDetails.subList(counter,
						incomeDetails.size() - counter));
			} else {
				this.deleteInBatch(incomeDetails.subList(counter, 1000));
			}
			counter += batchSize;
		}

	}

	private void deleteInBatch(List<IncomeDetail> incomeDetails) {
		if (incomeDetails.size() > 0) {
			Long[] idArray = new Long[incomeDetails.size()];
			for (int i = 0; i < incomeDetails.size(); i++) {
				idArray[i] = incomeDetails.get(i).getId();
			}
			String hqlQuery = String.format(
					"delete from %s where id in(:idList)",
					IncomeDetail.class.getName());
			Query query = this.getSession().createQuery(hqlQuery);
			query.setParameterList("idList", idArray);
			query.executeUpdate();
		}
	}

	public List<IncomeDetail> findByIncomeId(Long incomeId) {
		String hqlQuery = String.format(
				"select obj from %s obj where obj.income.id = :incomeId",
				IncomeDetail.class.getName());
		Query query = this.getSession().createQuery(hqlQuery)
				.setLong("incomeId", incomeId);
		return query.list();
	}
}