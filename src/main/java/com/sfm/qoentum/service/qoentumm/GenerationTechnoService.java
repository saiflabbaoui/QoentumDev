package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;

public interface GenerationTechnoService {
	
	GenerationTechno save(GenerationTechno generationTechno);
	
    List<GenerationTechno> findAll();
    
    List<GenerationTechno> findListMobile();
    
    void delete(long id);
    
    GenerationTechno findById(Long id);
    
    long count();
    
    EntityPage<GenerationTechno> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsGenerationTechnoByGeneration(String generation, Long id);
    
    GenerationTechno findByGeneration(String generation);
    
}
