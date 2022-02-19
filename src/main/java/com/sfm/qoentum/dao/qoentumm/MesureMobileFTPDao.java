package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileFTP;

@Repository
public interface MesureMobileFTPDao extends JpaRepository<MesureMobileFTP, Long> {

}
