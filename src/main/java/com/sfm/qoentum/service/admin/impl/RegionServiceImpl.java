package com.sfm.qoentum.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.admin.RegionDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.Region;
import com.sfm.qoentum.service.admin.RegionService;


@Service(value = "regionService")
public class RegionServiceImpl implements RegionService {
	
	@Autowired
	private RegionDao regionDao;

	@Override
	public Region save(Region region) {
		return regionDao.save(region);
	}

	@Override
	public List<Region> findAll() {
		List<Region> list = new ArrayList<>();
		regionDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		regionDao.deleteById(id);
		
	}

	@Override
	public Region findById(Long id) {
		return regionDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return regionDao.count();
	}
	
	@Override
	public EntityPage<Region> findByNomContaining(String nom, Pageable pageable) {
		
		Page<Region> regionsPage = regionDao.findByNomContaining(nom, pageable);
		
		EntityPage<Region> regions = new EntityPage<Region>();
		
		regions.setList(regionsPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(regionsPage.getNumberOfElements());
		pageUtil.setNombrePage(regionsPage.getTotalPages());
		pageUtil.setNumeroPage(regionsPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(regionsPage.getTotalElements());
		
		regions.setPageUtil(pageUtil);
		
		return regions;
	}
	
	@Override
	public boolean existsRegionByNom(String nom, Long id) {
		if(id == 0) {
			return regionDao.existsRegionByNom(nom);
		} else {
			return regionDao.existsRegionByNomAndIdIsNot(nom, id);
		}
		
	}
}
