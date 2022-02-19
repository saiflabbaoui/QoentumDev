package com.sfm.qoentum.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.admin.PaysDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.Pays;
import com.sfm.qoentum.service.admin.PaysService;


@Service(value = "paysService")
public class PaysServiceImpl implements PaysService {
	
	@Autowired
	private PaysDao paysDao;

	@Override
	public Pays save(Pays pays) {
		return paysDao.save(pays);
	}

	@Override
	public List<Pays> findAll() {
		List<Pays> list = new ArrayList<>();
		paysDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		paysDao.deleteById(id);
		
	}

	@Override
	public Pays findById(Long id) {
		return paysDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return paysDao.count();
	}
	
	@Override
	public EntityPage<Pays> findByNomContaining(String nom, Pageable pageable) {
		
		Page<Pays> payssPage = paysDao.findByNomContaining(nom, pageable);
		
		EntityPage<Pays> payss = new EntityPage<Pays>();
		
		payss.setList(payssPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(payssPage.getNumberOfElements());
		pageUtil.setNombrePage(payssPage.getTotalPages());
		pageUtil.setNumeroPage(payssPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(payssPage.getTotalElements());
		
		payss.setPageUtil(pageUtil);
		
		return payss;
	}
	
	@Override
	public boolean existsPaysByNom(String nom, Long id) {
		if (id == 0 ) {
			return paysDao.existsPaysByNom(nom);
		} else {
			return paysDao.existsPaysByNomAndIdIsNot(nom, id);
		}
	}
}
