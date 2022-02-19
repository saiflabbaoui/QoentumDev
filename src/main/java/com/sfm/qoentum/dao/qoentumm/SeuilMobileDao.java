package com.sfm.qoentum.dao.qoentumm;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.IndicateurMobile;
import com.sfm.qoentum.model.qoentumm.SeuilMobile;

@Repository
public interface SeuilMobileDao extends JpaRepository<SeuilMobile, Long> {
	
	Page<SeuilMobile> findByUserAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(User user, String nom, Pageable pageable);
	Page<SeuilMobile> findByUserAndGenerationTechnoAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(User user, GenerationTechno generationTechno, String nom, Pageable pageable);
	Page<SeuilMobile> findByUserAndIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(User user, IndicateurMobile indicateurMobile, String nom, Pageable pageable);
	Page<SeuilMobile> findByUserAndGenerationTechnoAndIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(User user, GenerationTechno generationTechno, IndicateurMobile indicateurMobile, String nom, Pageable pageable);

	
	Page<SeuilMobile> findByLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(String nom, Pageable pageable);
	Page<SeuilMobile> findByGenerationTechnoAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(GenerationTechno generationTechno, String nom, Pageable pageable);
	Page<SeuilMobile> findByIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc(IndicateurMobile indicateurMobile, String nom, Pageable pageable);
	Page<SeuilMobile> findByGenerationTechnoAndIndicateurMobileAndLibelleContainingOrderByIndicateurMobileAscGenerationTechnoAsc( GenerationTechno generationTechno, IndicateurMobile indicateurMobile, String nom, Pageable pageable);

	
	
	boolean existsSeuilMobileByLibelleAndIndicateurMobileAndUserAndGenerationTechno(String libelle, IndicateurMobile indicateurMobile,
			User user,GenerationTechno generationTechno);
	
	boolean existsSeuilMobileByLibelleAndIndicateurMobileAndUserAndGenerationTechnoAndIdIsNot(String libelle, IndicateurMobile indicateurMobile,
			User user, GenerationTechno generationTechno, Long id);
	
	List<SeuilMobile> findByIndicateurMobileIdAndGenerationTechnoIdAndDefaut(Long indicateurId, Long generationTechnoId, Boolean defaut);
}
