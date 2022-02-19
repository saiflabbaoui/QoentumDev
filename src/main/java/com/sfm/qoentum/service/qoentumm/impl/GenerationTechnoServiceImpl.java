package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.GenerationTechnoDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.service.qoentumm.GenerationTechnoService;


@Service(value = "generationTechnoService")
public class GenerationTechnoServiceImpl implements GenerationTechnoService {
	
	@Autowired
	private GenerationTechnoDao generationTechnoDao;

	@Override
	public GenerationTechno save(GenerationTechno generationTechno) {
		return generationTechnoDao.save(generationTechno);
	}

	@Override
	public List<GenerationTechno> findAll() {
		List<GenerationTechno> list = new ArrayList<>();
		generationTechnoDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public List<GenerationTechno> findListMobile() {
		List<GenerationTechno> list = new ArrayList<>();
		generationTechnoDao.findByGenerationIsNotAndGenerationIsNot("WIFI", "N/A").iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		generationTechnoDao.deleteById(id);
		
	}

	@Override
	public GenerationTechno findById(Long id) {
		return generationTechnoDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return generationTechnoDao.count();
	}
	
	@Override
	public GenerationTechno findByGeneration(String generation) {
		return generationTechnoDao.findByGeneration(generation);
	}
	
	
	@Override
	public EntityPage<GenerationTechno> findByNomContaining(String nom, Pageable pageable) {
		
		Page<GenerationTechno> generationTechnosPage = generationTechnoDao.findByGenerationContaining(nom, pageable);
		
		EntityPage<GenerationTechno> generationTechnos = new EntityPage<GenerationTechno>();
		
		generationTechnos.setList(generationTechnosPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(generationTechnosPage.getNumberOfElements());
		pageUtil.setNombrePage(generationTechnosPage.getTotalPages());
		pageUtil.setNumeroPage(generationTechnosPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(generationTechnosPage.getTotalElements());
		
		generationTechnos.setPageUtil(pageUtil);
		
		return generationTechnos;
	}
	
	@Override
	public boolean existsGenerationTechnoByGeneration(String generation, Long id) {
		if (id==0) {
			return generationTechnoDao.existsGenerationTechnoByGeneration(generation);
		} else {
			return generationTechnoDao.existsGenerationTechnoByGenerationAndIdIsNot(generation, id);
		}
	}
}
