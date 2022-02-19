package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.IndicateurMobile;

@Repository
public interface IndicateurMobileDao extends JpaRepository<IndicateurMobile, Long> {
	Page<IndicateurMobile> findByLibelleContaining(String libelle, Pageable pageable);
	boolean existsIndicateurMobileByLibelle(String libelle);
	boolean existsIndicateurMobileByLibelleAndIdIsNot(String libelle, Long id);
}
