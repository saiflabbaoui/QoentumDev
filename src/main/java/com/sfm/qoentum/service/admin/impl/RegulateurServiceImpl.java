package com.sfm.qoentum.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.admin.RegulateurDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.service.admin.RegulateurService;


@Service(value = "regulateurService")
public class RegulateurServiceImpl implements RegulateurService {
	
	@Autowired
	private RegulateurDao regulateurDao;

	@Override
	public Regulateur save(Regulateur regulateur) {
		return regulateurDao.save(regulateur);
	}

	@Override
	public List<Regulateur> findAll() {
		List<Regulateur> list = new ArrayList<>();
		regulateurDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		regulateurDao.deleteById(id);
		
	}

	@Override
	public Regulateur findById(Long id) {
		return regulateurDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return regulateurDao.count();
	}
	
	@Override
	public EntityPage<Regulateur> findByNomContaining(String nom, Pageable pageable) {
		
		Page<Regulateur> regulateursPage = regulateurDao.findByNomContaining(nom, pageable);
		
		EntityPage<Regulateur> regulateurs = new EntityPage<Regulateur>();
		
		regulateurs.setList(regulateursPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(regulateursPage.getNumberOfElements());
		pageUtil.setNombrePage(regulateursPage.getTotalPages());
		pageUtil.setNumeroPage(regulateursPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(regulateursPage.getTotalElements());
		
		regulateurs.setPageUtil(pageUtil);
		
		return regulateurs;
	}
	
	@Override
	public EntityPage<Regulateur> findByClient(Client client, Pageable pageable) {
		
		Page<Regulateur> regulateursPage = regulateurDao.findByClients(client, pageable);
		
		EntityPage<Regulateur> regulateurs = new EntityPage<Regulateur>();
		
		regulateurs.setList(regulateursPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(regulateursPage.getNumberOfElements());
		pageUtil.setNombrePage(regulateursPage.getTotalPages());
		pageUtil.setNumeroPage(regulateursPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(regulateursPage.getTotalElements());
		
		regulateurs.setPageUtil(pageUtil);
		
		return regulateurs;
	}
}
