package com.sfm.qoentum.service.qoentumf;

import java.util.List;

import com.sfm.qoentum.model.qoentumf.BrutMesureFixe;

public interface BrutMesureFixeService {
	
	BrutMesureFixe save(BrutMesureFixe brutMesureFixe);
	
    List<BrutMesureFixe> findAll();
    
    List<BrutMesureFixe> findAll(int nombreElement);
    
    void delete(long id);
    
    BrutMesureFixe findById(Long id);
    
    long count();
    
}
