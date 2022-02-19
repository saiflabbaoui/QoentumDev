package com.sfm.qoentum.service.qoentumf;

import java.util.List;

import com.sfm.qoentum.model.qoentumf.Parametre;

public interface ParametreService {
	
	Parametre save(Parametre parametre);
	
    List<Parametre> findAll();
    
    void delete(long id);
    
    Parametre findById(Long id);
}
