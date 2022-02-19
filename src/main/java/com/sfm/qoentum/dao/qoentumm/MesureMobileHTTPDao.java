package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileHTTP;

@Repository
public interface MesureMobileHTTPDao extends JpaRepository<MesureMobileHTTP, Long> {

}
