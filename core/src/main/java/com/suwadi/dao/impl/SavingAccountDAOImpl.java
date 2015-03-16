package com.suwadi.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SavingAccount;
import com.suwadi.utils.spring.CustomPropertyPlaceholderConfigurer;
import com.suwadi.web.model.microfinance.SavingAccountListDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("savingAccountDAO")
public class SavingAccountDAOImpl extends
		GenericHibernateDAOSupport<SavingAccount> implements SavingAccountDAO {
	CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer;

	@Autowired
	public void setPropertyPlaceholderConfigurer(
			CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer) {
		this.propertyPlaceholderConfigurer = propertyPlaceholderConfigurer;
	}

	public SavingAccountDAOImpl() {
		super(SavingAccount.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PagedResultSet paginateSavingAccount(Pager pager) {
		List<SavingAccountListDisplay> lst = new ArrayList<SavingAccountListDisplay>();
		String sqlQuery = this.propertyPlaceholderConfigurer
				.getProperty("savingAccount.findAll");
		String countSqlQuery = this.propertyPlaceholderConfigurer
				.getProperty("savingAccount.findAll.count");
		Query query = this.getSession().createSQLQuery(sqlQuery)
				.addScalar("id", Hibernate.LONG)
				.addScalar("first_name", Hibernate.STRING)
				.addScalar("last_name", Hibernate.STRING)
				.addScalar("nic_no", Hibernate.STRING)
				.addScalar("balance", Hibernate.BIG_DECIMAL)
				.addScalar("from_date", Hibernate.DATE)
				.addScalar("account_number", Hibernate.STRING);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List results = query.list();
		for (Object obj : results) {
			Map<String, Object> row = (Map<String, Object>) obj;
			SavingAccountListDisplay savingAccountListDisplay = new SavingAccountListDisplay();
			savingAccountListDisplay.setAccountHolderName((String) row
					.get("first_name") == null ? ""
					: (String) row.get("first_name") + " "
							+ (String) row.get("last_name") == null ? ""
							: (String) row.get("last_name"));
			savingAccountListDisplay.setAccountNumber((String) row
					.get("account_number"));
			savingAccountListDisplay.setOpenedAt((Date) row.get("from_date"));
			savingAccountListDisplay.setSavingAccountId((Long) row.get("id"));
			savingAccountListDisplay
					.setBalance((BigDecimal) row.get("balance"));
			lst.add(savingAccountListDisplay);
		}

		BigInteger cnt = (BigInteger) this.getSession()
				.createSQLQuery(countSqlQuery).uniqueResult();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(lst);
		pr.setRowCount(cnt.intValue());
		return pr;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SavingAccountListDisplay> beneficiarySavingAccounts(
			Long beneficiaryId) {
		List<SavingAccountListDisplay> lst = new ArrayList<SavingAccountListDisplay>();
		String sqlQuery = this.propertyPlaceholderConfigurer
				.getProperty("savingAccount.findByBeneficiaryId");
		Query query = this.getSession().createSQLQuery(sqlQuery)
				.addScalar("id", Hibernate.LONG)
				.addScalar("first_name", Hibernate.STRING)
				.addScalar("last_name", Hibernate.STRING)
				.addScalar("nic_no", Hibernate.STRING)
				.addScalar("balance", Hibernate.BIG_DECIMAL)
				.addScalar("from_date", Hibernate.DATE)
				.addScalar("account_number", Hibernate.STRING);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setLong("beneficiaryId", beneficiaryId);
		List results = query.list();
		for (Object obj : results) {
			Map<String, Object> row = (Map<String, Object>) obj;
			SavingAccountListDisplay savingAccountListDisplay = new SavingAccountListDisplay();
			savingAccountListDisplay.setAccountHolderName((String) row
					.get("first_name") == null ? ""
					: (String) row.get("first_name") + " "
							+ (String) row.get("last_name") == null ? ""
							: (String) row.get("last_name"));
			savingAccountListDisplay.setAccountNumber((String) row
					.get("account_number"));
			savingAccountListDisplay.setOpenedAt((Date) row.get("from_date"));
			savingAccountListDisplay.setSavingAccountId((Long) row.get("id"));
			savingAccountListDisplay
					.setBalance((BigDecimal) row.get("balance"));
			lst.add(savingAccountListDisplay);
		}

		return lst;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SavingAccountListDisplay> societySavingAccounts(Long societyId) {
		List<SavingAccountListDisplay> lst = new ArrayList<SavingAccountListDisplay>();
		String sqlQuery = this.propertyPlaceholderConfigurer
				.getProperty("savingAccount.findBySocietyId");
		Query query = this.getSession().createSQLQuery(sqlQuery)
				.addScalar("id", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("balance", Hibernate.BIG_DECIMAL)
				.addScalar("from_date", Hibernate.DATE)
				.addScalar("account_number", Hibernate.STRING);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setLong("societyId", societyId);
		List results = query.list();
		for (Object obj : results) {
			Map<String, Object> row = (Map<String, Object>) obj;
			SavingAccountListDisplay savingAccountListDisplay = new SavingAccountListDisplay();
			savingAccountListDisplay.setAccountHolderName((String) row
					.get("name"));
			savingAccountListDisplay.setAccountNumber((String) row
					.get("account_number"));
			savingAccountListDisplay.setOpenedAt((Date) row.get("from_date"));
			savingAccountListDisplay.setSavingAccountId((Long) row.get("id"));
			savingAccountListDisplay
					.setBalance((BigDecimal) row.get("balance"));
			lst.add(savingAccountListDisplay);
		}

		return lst;
	}
}
