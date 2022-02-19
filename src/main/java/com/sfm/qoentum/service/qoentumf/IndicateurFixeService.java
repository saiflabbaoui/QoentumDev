package com.sfm.qoentum.service.qoentumf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumf.IndicateurFixe;

public interface IndicateurFixeService {
	
	IndicateurFixe save(IndicateurFixe indicateurFixe);
	
    List<IndicateurFixe> findAll();
    
    void delete(long id);
    
    IndicateurFixe findById(Long id);
    
    long count();
    
    EntityPage<IndicateurFixe> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsIndicateurFixeByLibelle(String libelle, Long id);
    
}
