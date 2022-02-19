package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumf.Emplacement;

@Repository
public interface EmplacementDao extends JpaRepository<Emplacement, Long> {
	Page<Emplacement> findByNomContaining(String nom, Pageable pageable);
	
	boolean existsEmplacementByNom(String nom);
	boolean existsEmplacementByNomAndIdIsNot(String nom, Long id);
	Integer countEmplacementByEnabled(boolean e);

}
