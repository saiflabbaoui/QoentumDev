package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumf.IndicateurFixe;

@Repository
public interface IndicateurFixeDao extends JpaRepository<IndicateurFixe, Long> {
	Page<IndicateurFixe> findByLibelleContaining(String libelle, Pageable pageable);
	boolean existsIndicateurFixeByLibelle(String libelle);
	boolean existsIndicateurFixeByLibelleAndIdIsNot(String libelle, Long id);
}
