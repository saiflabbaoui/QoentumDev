package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileHTTP;

public interface MesureMobileHTTPService {
	
	MesureMobileHTTP save(MesureMobileHTTP mesureMobileHTTP);
	
    List<MesureMobileHTTP> findAll();
    
    void delete(long id);
    
    MesureMobileHTTP findById(Long id);
    
    long count();
    
}
