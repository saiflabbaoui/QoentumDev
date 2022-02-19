package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumf.BrutMesureFixe;

@Repository
public interface BrutMesureFixeDao extends JpaRepository< BrutMesureFixe, Long> {

}
