package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumf.IndicateurFixe;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;

@Repository
public interface SeuilFixeDao extends JpaRepository<SeuilFixe, Long> {
	
	Page<SeuilFixe> findByLibelleContainingOrderByIndicateurFixeAsc(String libelle, Pageable pageable);
	Page<SeuilFixe> findByLibelleContainingAndIndicateurFixeIdOrderByIndicateurFixeAsc(String libelle, long indicateur_fixe_id, Pageable pageable);
	Page<SeuilFixe> findByUserAndLibelleContainingOrderByIndicateurFixeAsc(User user, String libelle, Pageable pageable);
	Page<SeuilFixe> findByUserAndLibelleContainingAndIndicateurFixeIdOrderByIndicateurFixeAsc(User user, String libelle, long indicateur_fixe_id, Pageable pageable);
	
	
	
	boolean existsSeuilFixeByLibelleAndIndicateurFixeAndUser(String libelle, IndicateurFixe indicateurFixe, User user);
	boolean existsSeuilFixeByLibelleAndIndicateurFixeAndUserAndIdIsNot(String libelle, IndicateurFixe indicateurFixe, User user, Long id);
}
