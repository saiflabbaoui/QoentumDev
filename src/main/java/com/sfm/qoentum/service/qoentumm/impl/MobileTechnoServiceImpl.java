package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.MobileTechnoDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumm.MobileTechno;
import com.sfm.qoentum.service.qoentumm.MobileTechnoService;


@Service(value = "mobileTechnoService")
public class MobileTechnoServiceImpl implements MobileTechnoService {
	
	@Autowired
	private MobileTechnoDao mobileTechnoDao;

	@Override
	public MobileTechno save(MobileTechno mobileTechno) {
		return mobileTechnoDao.save(mobileTechno);
	}

	@Override
	public List<MobileTechno> findAll() {
		List<MobileTechno> list = new ArrayList<>();
		mobileTechnoDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mobileTechnoDao.deleteById(id);
		
	}

	@Override
	public MobileTechno findById(Long id) {
		return mobileTechnoDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return mobileTechnoDao.count();
	}
	
	@Override
	public EntityPage<MobileTechno> findByNomContaining(String nom, Pageable pageable) {
		
		Page<MobileTechno> mobileTechnosPage = mobileTechnoDao.findByTechnologieContaining(nom, pageable);
		
		EntityPage<MobileTechno> mobileTechnos = new EntityPage<MobileTechno>();
		
		mobileTechnos.setList(mobileTechnosPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(mobileTechnosPage.getNumberOfElements());
		pageUtil.setNombrePage(mobileTechnosPage.getTotalPages());
		pageUtil.setNumeroPage(mobileTechnosPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(mobileTechnosPage.getTotalElements());
		
		mobileTechnos.setPageUtil(pageUtil);
		
		return mobileTechnos;
	}
}
