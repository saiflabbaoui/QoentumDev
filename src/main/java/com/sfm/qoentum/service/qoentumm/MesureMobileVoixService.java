package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileVoix;

public interface MesureMobileVoixService {
	
	MesureMobileVoix save(MesureMobileVoix mesureMobileVoix);
	
    List<MesureMobileVoix> findAll();
    
    void delete(long id);
    
    MesureMobileVoix findById(Long id);
    
    long count();
    
}
