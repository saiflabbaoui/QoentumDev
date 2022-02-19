package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileSMS;

public interface MesureMobileSMSService {
	
	MesureMobileSMS save(MesureMobileSMS mesureMobileSMS);
	
    List<MesureMobileSMS> findAll();
    
    void delete(long id);
    
    MesureMobileSMS findById(Long id);
    
    long count();
    
}
