package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.IndicateurMobileDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumm.IndicateurMobile;
import com.sfm.qoentum.service.qoentumm.IndicateurMobileService;


@Service(value = "indicateurMobileService")
public class IndicateurMobileServiceImpl implements IndicateurMobileService {
	
	@Autowired
	private IndicateurMobileDao indicateurMobileDao;

	@Override
	public IndicateurMobile save(IndicateurMobile indicateurMobile) {
		return indicateurMobileDao.save(indicateurMobile);
	}

	@Override
	public List<IndicateurMobile> findAll() {
		List<IndicateurMobile> list = new ArrayList<>();
		indicateurMobileDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		indicateurMobileDao.deleteById(id);
		
	}

	@Override
	public IndicateurMobile findById(Long id) {
		return indicateurMobileDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return indicateurMobileDao.count();
	}
	
	@Override
	public EntityPage<IndicateurMobile> findByNomContaining(String nom, Pageable pageable) {
		
		Page<IndicateurMobile> indicateurMobilesPage = indicateurMobileDao.findByLibelleContaining(nom, pageable);
		
		EntityPage<IndicateurMobile> indicateurMobiles = new EntityPage<IndicateurMobile>();
		
		indicateurMobiles.setList(indicateurMobilesPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(indicateurMobilesPage.getNumberOfElements());
		pageUtil.setNombrePage(indicateurMobilesPage.getTotalPages());
		pageUtil.setNumeroPage(indicateurMobilesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(indicateurMobilesPage.getTotalElements());
		
		indicateurMobiles.setPageUtil(pageUtil);
		
		return indicateurMobiles;
	}
	
	@Override
	public boolean existsIndicateurMobileByLibelle(String libelle, Long id) {
		if(id==0) {
			return indicateurMobileDao.existsIndicateurMobileByLibelle(libelle);
		} else {
			return indicateurMobileDao.existsIndicateurMobileByLibelleAndIdIsNot(libelle, id);
		}
	}
}
