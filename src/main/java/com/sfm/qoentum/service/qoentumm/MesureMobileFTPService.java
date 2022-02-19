package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileFTP;

public interface MesureMobileFTPService {
	
	MesureMobileFTP save(MesureMobileFTP mesureMobileFTP);
	
    List<MesureMobileFTP> findAll();
    
    void delete(long id);
    
    MesureMobileFTP findById(Long id);
    
    long count();
    
}
