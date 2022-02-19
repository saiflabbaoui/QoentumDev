package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileVideo;

public interface MesureMobileVideoService {
	
	MesureMobileVideo save(MesureMobileVideo mesureMobileVideo);
	
    List<MesureMobileVideo> findAll();
    
    void delete(long id);
    
    MesureMobileVideo findById(Long id);
    
    long count();
    
}
