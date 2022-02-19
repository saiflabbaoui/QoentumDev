package com.sfm.qoentum.service.qoentumf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumf.Emplacement;

public interface EmplacementService {
	
	Emplacement save(Emplacement emplacement);
	
    List<Emplacement> findAll();
    
    void delete(long id);
    
    Emplacement findById(Long id);
    
    long count();
    
    EntityPage<Emplacement> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsEmplacementByNom(String nom, Long id);
}
