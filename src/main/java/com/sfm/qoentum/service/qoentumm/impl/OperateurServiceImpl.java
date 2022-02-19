package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.OperateurDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.service.qoentumm.OperateurService;


@Service(value = "operateurService")
public class OperateurServiceImpl implements OperateurService {
	
	@Autowired
	private OperateurDao operateurDao;

	@Override
	public Operateur save(Operateur operateur) {
		return operateurDao.save(operateur);
	}

	@Override
	public List<Operateur> findAll() {
		List<Operateur> list = new ArrayList<>();
		operateurDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		operateurDao.deleteById(id);
		
	}

	@Override
	public Operateur findById(Long id) {
		return operateurDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return operateurDao.count();
	}
	
	@Override
	public EntityPage<Operateur> findByNomContaining(String nom, Pageable pageable) {
		
		Page<Operateur> operateursPage = operateurDao.findByNomContainingOrMccContainingOrMncContaining(nom, nom, nom, pageable);
		
		EntityPage<Operateur> operateurs = new EntityPage<Operateur>();
		
		operateurs.setList(operateursPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(operateursPage.getNumberOfElements());
		pageUtil.setNombrePage(operateursPage.getTotalPages());
		pageUtil.setNumeroPage(operateursPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(operateursPage.getTotalElements());
		
		operateurs.setPageUtil(pageUtil);
		
		return operateurs;
	}
	
	@Override
	public EntityPage<Operateur> findByRegulateur(Regulateur regulateur, Pageable pageable) {
		
		Page<Operateur> operateursPage = operateurDao.findByRegulateur(regulateur, pageable);
		
		EntityPage<Operateur> operateurs = new EntityPage<Operateur>();
		
		operateurs.setList(operateursPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(operateursPage.getNumberOfElements());
		pageUtil.setNombrePage(operateursPage.getTotalPages());
		pageUtil.setNumeroPage(operateursPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(operateursPage.getTotalElements());
		
		operateurs.setPageUtil(pageUtil);
		
		return operateurs;
	}
	
	@Override
	public EntityPage<Operateur> findByNomContainingAndClient(Client client, String nom, Pageable pageable) {

		Page<Operateur> operateursPage = operateurDao.findByRegulateurClientsAndNomContainingOrMccContainingOrMncContaining(client, nom, nom, nom, pageable);
		
		EntityPage<Operateur> operateurs = new EntityPage<Operateur>();
		
		operateurs.setList(operateursPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(operateursPage.getNumberOfElements());
		pageUtil.setNombrePage(operateursPage.getTotalPages());
		pageUtil.setNumeroPage(operateursPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(operateursPage.getTotalElements());
		
		operateurs.setPageUtil(pageUtil);
		
		return operateurs;
	}
	
	@Override
	public Operateur findByMccAndMnc(String mcc, String mnc) {		
		return operateurDao.findByMccAndMnc(mcc, mnc);
	}
	
	@Override
	public boolean existsOperateurByNom(String nom, Long id) {
		if(id==0) {
			return operateurDao.existsOperateurByNom(nom);
		} else {
			return operateurDao.existsOperateurByNomAndIdIsNot(nom, id);
		}
	}
	
	@Override
	public boolean existsOperateurByMccAndMnc(String mcc, String mnc, Long id) {
		if(id==0) {
			return operateurDao.existsOperateurByMccAndMnc(mcc, mnc);
		} else {
			return operateurDao.existsOperateurByMccAndMncAndIdIsNot(mcc, mnc, id);
		}
	}
}
