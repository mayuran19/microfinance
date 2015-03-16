package com.suwadi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.DistrictDAO;
import com.suwadi.domain.District;
import com.suwadi.service.DistrictService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {
	private DistrictDAO districtDAO;

	public DistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	@Autowired
	public void setDistrictDAO(DistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public District save(District district) {
		return this.getDistrictDAO().save(district);
	}

	@Transactional
	public District update(District district) {
		this.getDistrictDAO().update(district);
		return district;
	}

	public District findById(Long id) {
		return this.getDistrictDAO().findById(id);
	}

	public District delete(District district) {
		return this.getDistrictDAO().delete(district);
	}

	public List<District> findAll() {
		return this.getDistrictDAO().findAll();
	}

	public List<District> findAll(Pager pager) {
		return this.getDistrictDAO().findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.getDistrictDAO().paginate(pager);
	}

	public Integer getAllCount() {
		return this.getDistrictDAO().getAllCount();
	}

	public List<District> findAllActiveDistrict() {
		return this.getDistrictDAO().findAllActiveDistrict();
	}
	
	public PagedResultSet findAllActiveDistrict(Pager pager) {
		return this.getDistrictDAO().findAllActiveDistrict(pager);
	}
	
	public List<com.suwadi.web.json.response.District> getAllActiveDistricts() {
		List<District> districts = this.findAllActiveDistrict();
		List<com.suwadi.web.json.response.District> jsonDistricts = new ArrayList<com.suwadi.web.json.response.District>();
		for (District district : districts) {
			com.suwadi.web.json.response.District jd = new com.suwadi.web.json.response.District();
			jd.setId(district.getId());
			jd.setName(district.getName());

			jsonDistricts.add(jd);
		}
		return jsonDistricts;
	}
	
	public List<com.suwadi.web.json.response.District> getAllJSONDistricts() {
		List<District> districts = this.findAll();
		List<com.suwadi.web.json.response.District> jsonDistricts = new ArrayList<com.suwadi.web.json.response.District>();
		for (District district : districts) {
			com.suwadi.web.json.response.District jd = new com.suwadi.web.json.response.District();
			jd.setId(district.getId());
			jd.setName(district.getName());

			jsonDistricts.add(jd);
		}
		return jsonDistricts;
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.districtDAO.isUnique(id, fieldName, fieldValue);
	}

	public District findById(Long id, String... include) {
		return this.districtDAO.findById(id, include);
	}

}
