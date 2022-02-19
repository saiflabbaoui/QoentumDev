package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.FournisseurAccesDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import com.sfm.qoentum.service.qoentumf.FournisseurAccesService;


@Service(value = "fournisseurAccesService")
public class FournisseurAccesServiceImpl implements FournisseurAccesService {
	
	@Autowired
	private FournisseurAccesDao fournisseurAccesDao;


	@Override
	public FournisseurAcces save(FournisseurAcces fournisseurAcces) {
		return fournisseurAccesDao.save(fournisseurAcces);
	}

	@Override
	public List<FournisseurAcces> findAll() {
		List<FournisseurAcces> list = new ArrayList<>();
		fournisseurAccesDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		fournisseurAccesDao.deleteById(id);
		
	}

	@Override
	public FournisseurAcces findById(Long id) {
		return fournisseurAccesDao.findById(id).get();
	}


	@Override
	public long count() {
		return fournisseurAccesDao.count();
	}
	
	@Override
	public EntityPage<FournisseurAcces> findByNomContaining(String nom, Pageable pageable) {
		
		Page<FournisseurAcces> fournisseurAccessPage = fournisseurAccesDao.findByNomContaining(nom, pageable);
		
		EntityPage<FournisseurAcces> fournisseurAccess = new EntityPage<FournisseurAcces>();
		
		fournisseurAccess.setList(fournisseurAccessPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(fournisseurAccessPage.getNumberOfElements());
		pageUtil.setNombrePage(fournisseurAccessPage.getTotalPages());
		pageUtil.setNumeroPage(fournisseurAccessPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(fournisseurAccessPage.getTotalElements());
		
		fournisseurAccess.setPageUtil(pageUtil);
		
		return fournisseurAccess;
	}
	
	@Override
	public EntityPage<FournisseurAcces> findByRegulateur(Regulateur regulateur, Pageable pageable) {
		
		Page<FournisseurAcces> fournisseurAccessPage = fournisseurAccesDao.findByRegulateur(regulateur, pageable);
		
		EntityPage<FournisseurAcces> fournisseurAccess = new EntityPage<FournisseurAcces>();
		
		fournisseurAccess.setList(fournisseurAccessPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(fournisseurAccessPage.getNumberOfElements());
		pageUtil.setNombrePage(fournisseurAccessPage.getTotalPages());
		pageUtil.setNumeroPage(fournisseurAccessPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(fournisseurAccessPage.getTotalElements());
		
		fournisseurAccess.setPageUtil(pageUtil);
		
		return fournisseurAccess;
	}
}
