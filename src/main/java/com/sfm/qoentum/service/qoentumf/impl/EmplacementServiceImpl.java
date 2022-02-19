package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.EmplacementDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumf.Emplacement;
import com.sfm.qoentum.service.qoentumf.EmplacementService;


@Service(value = "emplacementService")
public class EmplacementServiceImpl implements EmplacementService {
	
	@Autowired
	private EmplacementDao emplacementDao;

	@Override
	public Emplacement save(Emplacement emplacement) {
		return emplacementDao.save(emplacement);
	}

	@Override
	public List<Emplacement> findAll() {
		List<Emplacement> list = new ArrayList<>();
		emplacementDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		emplacementDao.deleteById(id);
		
	}

	@Override
	public Emplacement findById(Long id) {
		return emplacementDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return emplacementDao.count();
	}
	
	@Override
	public EntityPage<Emplacement> findByNomContaining(String nom, Pageable pageable) {
		
		Page<Emplacement> emplacementsPage = emplacementDao.findByNomContaining(nom, pageable);
		
		EntityPage<Emplacement> emplacements = new EntityPage<Emplacement>();
		
		emplacements.setList(emplacementsPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(emplacementsPage.getNumberOfElements());
		pageUtil.setNombrePage(emplacementsPage.getTotalPages());
		pageUtil.setNumeroPage(emplacementsPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(emplacementsPage.getTotalElements());
		
		emplacements.setPageUtil(pageUtil);
		
		return emplacements;
	}
	
	@Override
	public boolean existsEmplacementByNom(String nom, Long id) {
		if(id==0) {
			return emplacementDao.existsEmplacementByNom(nom);
		} else {
			return emplacementDao.existsEmplacementByNomAndIdIsNot(nom, id);
		}
	}
}
