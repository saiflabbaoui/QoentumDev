package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;

@Repository
public interface FournisseurAccesDao extends JpaRepository<FournisseurAcces, Long> {
	Page<FournisseurAcces> findByNomContaining(String nom, Pageable pageable);
	Page<FournisseurAcces> findByRegulateur(Regulateur regulateur, Pageable pageable);
}
