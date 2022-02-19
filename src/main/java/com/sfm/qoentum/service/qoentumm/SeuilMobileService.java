package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.IndicateurMobile;
import com.sfm.qoentum.model.qoentumm.SeuilMobile;

public interface SeuilMobileService {

	SeuilMobile save(SeuilMobile seuilMobile);

	List<SeuilMobile> findAll();

	void delete(long id);

	SeuilMobile findById(Long id);

	long count();

	EntityPage<SeuilMobile> findByNomContaining(User user, IndicateurMobile indicateurMobile, GenerationTechno generationTechno, String nom,
			Pageable pageable);

	boolean existsPlageSeuilMobileByLibelleAndIndicateurMobileAndUserAndGenerationTechno(String libelle, IndicateurMobile indicateurMobile,
			User user, GenerationTechno generationTechno, Long id);
	
	List<Long> findDefaultSeuilByIndicateur(long generationId, long indicateurId);
}
