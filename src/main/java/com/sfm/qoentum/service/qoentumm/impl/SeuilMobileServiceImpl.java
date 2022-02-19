package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.SeuilMobileDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.IndicateurMobile;
import com.sfm.qoentum.model.qoentumm.SeuilMobile;
import com.sfm.qoentum.service.qoentumm.SeuilMobileService;

@Service(value = "plageSeuilMobileService")
public class SeuilMobileServiceImpl implements SeuilMobileService {

	@Autowired
	private SeuilMobileDao seuilMobileDao;

	@Override
	public SeuilMobile save(SeuilMobile seuilMobile) {
		return seuilMobileDao.save(seuilMobile);
	}

	@Override
	public List<SeuilMobile> findAll() {
		List<SeuilMobile> list = new ArrayList<>();
		seuilMobileDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		seuilMobileDao.deleteById(id);

	}

	@Override
	public SeuilMobile findById(Long id) {
		return seuilMobileDao.findById(id).get();
	}

	@Override
	public long count() {
		return seuilMobileDao.count();
	}

	@Override
	public EntityPage<SeuilMobile> findByNomContaining(User user, IndicateurMobile indicateurMobile,
			GenerationTechno generationTechno, String nom, Pageable pageable) {

		Page<SeuilMobile> seuilMobilesPage = null;

		if (user.isSfm()) {

			if ((generationTechno == null) && (indicateurMobile == null)) {
				seuilMobilesPage = seuilMobileDao.findByLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(nom, pageable);
			}

			if ((generationTechno == null) && (indicateurMobile != null)) {
				seuilMobilesPage = seuilMobileDao.findByIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(indicateurMobile, nom,
						pageable);
			}

			if ((generationTechno != null) && (indicateurMobile == null)) {
				seuilMobilesPage = seuilMobileDao.findByGenerationTechnoAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(generationTechno, nom,
						pageable);
			}

			if ((generationTechno != null) && (indicateurMobile != null)) {
				seuilMobilesPage = seuilMobileDao.findByUserAndGenerationTechnoAndIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(
						user, generationTechno, indicateurMobile, nom, pageable);
			}

		} else {
			if ((generationTechno == null) && (indicateurMobile == null)) {
				seuilMobilesPage = seuilMobileDao.findByUserAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(user, nom, pageable);
			}

			if ((generationTechno == null) && (indicateurMobile != null)) {
				seuilMobilesPage = seuilMobileDao.findByUserAndIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(user,
						indicateurMobile, nom, pageable);
			}

			if ((generationTechno != null) && (indicateurMobile == null)) {
				seuilMobilesPage = seuilMobileDao.findByUserAndGenerationTechnoAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(user,
						generationTechno, nom, pageable);
			}

			if ((generationTechno != null) && (indicateurMobile != null)) {
				seuilMobilesPage = seuilMobileDao.findByUserAndGenerationTechnoAndIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(
						user, generationTechno, indicateurMobile, nom, pageable);
			}
		}

		EntityPage<SeuilMobile> seuilMobiles = new EntityPage<SeuilMobile>();

		seuilMobiles.setList(seuilMobilesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(seuilMobilesPage.getNumberOfElements());
		pageUtil.setNombrePage(seuilMobilesPage.getTotalPages());
		pageUtil.setNumeroPage(seuilMobilesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(seuilMobilesPage.getTotalElements());

		seuilMobiles.setPageUtil(pageUtil);

		return seuilMobiles;
	}

	@Override
	public boolean existsPlageSeuilMobileByLibelleAndIndicateurMobileAndUserAndGenerationTechno(String libelle,
			IndicateurMobile indicateurMobile, User user, GenerationTechno generationTechno, Long id) {
		if (id == 0) {
			return seuilMobileDao.existsSeuilMobileByLibelleAndIndicateurMobileAndUserAndGenerationTechno(libelle,
					indicateurMobile, user, generationTechno);
		} else {
			return seuilMobileDao.existsSeuilMobileByLibelleAndIndicateurMobileAndUserAndGenerationTechnoAndIdIsNot(
					libelle, indicateurMobile, user, generationTechno, id);
		}

	}
	
	@Override
	public List<Long> findDefaultSeuilByIndicateur(long genetionId, long indicateurId) {
		
		List<Long> retour = new ArrayList<>();
		
		List<SeuilMobile> seuils = seuilMobileDao.findByIndicateurMobileIdAndGenerationTechnoIdAndDefaut(indicateurId, genetionId, true);
		
		if (!seuils.isEmpty()) {
			for (SeuilMobile seuilMobile : seuils) {
				retour.add(seuilMobile.getId());
			}
		}
		
		return retour;
	}
}
