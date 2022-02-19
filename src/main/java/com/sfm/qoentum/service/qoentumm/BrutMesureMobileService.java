package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import com.sfm.qoentum.model.qoentumm.BrutMesureMobile;

public interface BrutMesureMobileService {
	
	BrutMesureMobile save(BrutMesureMobile brutMesureMobile);
	
    List<BrutMesureMobile> findAll();
    
    List<BrutMesureMobile> findAll(int nombreElement);
    
    void delete(long id);
    
    BrutMesureMobile findById(Long id);
    
    long count();

    String generateToken();
    
}
