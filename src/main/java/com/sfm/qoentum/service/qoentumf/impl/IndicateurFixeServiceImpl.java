package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.IndicateurFixeDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumf.IndicateurFixe;
import com.sfm.qoentum.service.qoentumf.IndicateurFixeService;


@Service(value = "indicateurFixeService")
public class IndicateurFixeServiceImpl implements IndicateurFixeService {
	
	@Autowired
	private IndicateurFixeDao indicateurFixeDao;

	@Override
	public IndicateurFixe save(IndicateurFixe indicateurFixe) {
		return indicateurFixeDao.save(indicateurFixe);
	}

	@Override
	public List<IndicateurFixe> findAll() {
		List<IndicateurFixe> list = new ArrayList<>();
		indicateurFixeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		indicateurFixeDao.deleteById(id);
		
	}

	@Override
	public IndicateurFixe findById(Long id) {
		return indicateurFixeDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return indicateurFixeDao.count();
	}
	
	@Override
	public EntityPage<IndicateurFixe> findByNomContaining(String nom, Pageable pageable) {
		
		Page<IndicateurFixe> indicateurFixesPage = indicateurFixeDao.findByLibelleContaining(nom, pageable);
		
		EntityPage<IndicateurFixe> indicateurFixes = new EntityPage<IndicateurFixe>();
		
		indicateurFixes.setList(indicateurFixesPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(indicateurFixesPage.getNumberOfElements());
		pageUtil.setNombrePage(indicateurFixesPage.getTotalPages());
		pageUtil.setNumeroPage(indicateurFixesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(indicateurFixesPage.getTotalElements());
		
		indicateurFixes.setPageUtil(pageUtil);
		
		return indicateurFixes;
	}
	
	@Override
	public boolean existsIndicateurFixeByLibelle(String libelle, Long id) {
		if (id ==0) {
			return indicateurFixeDao.existsIndicateurFixeByLibelle(libelle);
		} else {
			return indicateurFixeDao.existsIndicateurFixeByLibelleAndIdIsNot(libelle, id);
		}
	}
}
