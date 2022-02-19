package com.sfm.qoentum.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.Pays;

@Repository
public interface PaysDao extends JpaRepository<Pays, Long> {
	Page<Pays> findByNomContaining(String nom, Pageable pageable);
	boolean existsPaysByNom(String nom);
	boolean existsPaysByNomAndIdIsNot(String nom, Long id);
}
