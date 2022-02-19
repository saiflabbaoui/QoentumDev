package com.sfm.qoentum.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.Region;

@Repository
public interface RegionDao extends JpaRepository<Region, Long> {
	Page<Region> findByNomContaining(String nom, Pageable pageable);
	boolean existsRegionByNom(String nom);
	boolean existsRegionByNomAndIdIsNot(String nom, Long id);
}
