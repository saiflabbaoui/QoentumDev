package com.sfm.qoentum.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.admin.ContinentDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.Continent;
import com.sfm.qoentum.service.admin.ContinentService;


@Service(value = "continentService")
public class ContinentServiceImpl implements ContinentService {
	
	@Autowired
	private ContinentDao continentDao;

	@Override
	public Continent save(Continent continent) {
		return continentDao.save(continent);
	}

	@Override
	public List<Continent> findAll() {
		List<Continent> list = new ArrayList<>();
		continentDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		continentDao.deleteById(id);
		
	}

	@Override
	public Continent findById(Long id) {
		return continentDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return continentDao.count();
	}
	
	@Override
	public EntityPage<Continent> findByNomContaining(String nom, Pageable pageable) {
		
		Page<Continent> continentsPage = continentDao.findByNomContaining(nom, pageable);
		
		EntityPage<Continent> continents = new EntityPage<Continent>();
		
		continents.setList(continentsPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(continentsPage.getNumberOfElements());
		pageUtil.setNombrePage(continentsPage.getTotalPages());
		pageUtil.setNumeroPage(continentsPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(continentsPage.getTotalElements());
		
		continents.setPageUtil(pageUtil);
		
		return continents;
	}
	
	@Override
	public boolean existsContinentByNom(String nom, Long id) {
		if(id == 0) {
			return continentDao.existsContinentByNom(nom);
		} else {
			return continentDao.existsContinentByNomAndIdIsNot(nom, id);
		}
		
	}
}
