package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.MobileTechno;

public interface MobileTechnoService {
	
	MobileTechno save(MobileTechno mobileTechno);
	
    List<MobileTechno> findAll();
    
    void delete(long id);
    
    MobileTechno findById(Long id);
    
    long count();
    
    EntityPage<MobileTechno> findByNomContaining(String nom, Pageable pageable);
    
}
