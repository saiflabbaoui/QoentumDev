package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumf.Parametre;

@Repository
public interface ParametreDao extends JpaRepository<Parametre, Long> {
	
}
