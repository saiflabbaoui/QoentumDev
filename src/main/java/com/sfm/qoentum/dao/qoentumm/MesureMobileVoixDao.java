package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileVoix;

@Repository
public interface MesureMobileVoixDao extends JpaRepository<MesureMobileVoix, Long> {

}
