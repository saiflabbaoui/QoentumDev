package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.IndicateurMobile;

public interface IndicateurMobileService {
	
	IndicateurMobile save(IndicateurMobile indicateurMobile);
	
    List<IndicateurMobile> findAll();
    
    void delete(long id);
    
    IndicateurMobile findById(Long id);
    
    long count();
    
    EntityPage<IndicateurMobile> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsIndicateurMobileByLibelle(String libelle, Long id);
    
}
