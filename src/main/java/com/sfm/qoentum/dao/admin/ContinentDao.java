package com.sfm.qoentum.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.Continent;

@Repository
public interface ContinentDao extends JpaRepository<Continent, Long> {
	Page<Continent> findByNomContaining(String nom, Pageable pageable);
	boolean existsContinentByNom(String nom);
	boolean existsContinentByNomAndIdIsNot(String nom, Long id);
}
