package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.admin.UserDao;
import com.sfm.qoentum.dao.qoentumf.SeuilFixeDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumf.IndicateurFixe;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;
import com.sfm.qoentum.service.qoentumf.SeuilFixeService;


@Service(value = "plageSeuilFixeService")
public class SeuilFixeServiceImpl implements SeuilFixeService {
	
	@Autowired
	private SeuilFixeDao seuilFixeDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public SeuilFixe save(SeuilFixe seuilFixe) {
		return seuilFixeDao.save(seuilFixe);
	}

	@Override
	public List<SeuilFixe> findAll() {
		List<SeuilFixe> list = new ArrayList<>();
		seuilFixeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		seuilFixeDao.deleteById(id);
		
	}

	@Override
	public SeuilFixe findById(Long id) {
		return seuilFixeDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return seuilFixeDao.count();
	}
	
	@Override
	public EntityPage<SeuilFixe> findByLibelleContaining(String nom, long user_id, Long indicateur_fixe_id, Pageable pageable) {
		
		Page<SeuilFixe> seuilFixesPage = null;
		
		User user = userDao.findById(user_id).get();
		
		if (user.isSfm()) {
			
			if(indicateur_fixe_id==null) {
				seuilFixesPage = seuilFixeDao.findByLibelleContainingOrderByIndicateurFixeAsc(nom, pageable);
			} else {
				seuilFixesPage = seuilFixeDao.findByLibelleContainingAndIndicateurFixeIdOrderByIndicateurFixeAsc(nom, indicateur_fixe_id, pageable);
			}
		} else {
			if(indicateur_fixe_id==null) {
				seuilFixesPage = seuilFixeDao.findByUserAndLibelleContainingOrderByIndicateurFixeAsc(user, nom, pageable);
			} else {
				seuilFixesPage = seuilFixeDao.findByUserAndLibelleContainingAndIndicateurFixeIdOrderByIndicateurFixeAsc(user, nom, indicateur_fixe_id, pageable);
			}
		}	
		
		EntityPage<SeuilFixe> seuilFixes = new EntityPage<SeuilFixe>();
		
		seuilFixes.setList(seuilFixesPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(seuilFixesPage.getNumberOfElements());
		pageUtil.setNombrePage(seuilFixesPage.getTotalPages());
		pageUtil.setNumeroPage(seuilFixesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(seuilFixesPage.getTotalElements());
		
		seuilFixes.setPageUtil(pageUtil);
		
		return seuilFixes;
	}
	
	@Override
	public boolean existsPlageSeuilFixeByLibelleAndIndicateurFixeAndUser(String libelle, IndicateurFixe indicateurFixe,
			User user, Long id) {
		if(id==0) {
			return seuilFixeDao.existsSeuilFixeByLibelleAndIndicateurFixeAndUser(libelle, indicateurFixe, user);
		} else {
			return seuilFixeDao.existsSeuilFixeByLibelleAndIndicateurFixeAndUserAndIdIsNot(libelle, indicateurFixe, user, id);
		}
	}
}
