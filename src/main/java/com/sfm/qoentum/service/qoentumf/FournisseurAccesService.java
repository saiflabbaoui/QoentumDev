package com.sfm.qoentum.service.qoentumf;

import java.util.List;


import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;

public interface FournisseurAccesService {
	
	FournisseurAcces save(FournisseurAcces fournisseurAcces);
	
    List<FournisseurAcces> findAll();
    
    void delete(long id);
    
    FournisseurAcces findById(Long id);
    
    long count();
    
    EntityPage<FournisseurAcces> findByNomContaining(String nom, Pageable pageable);
    
    EntityPage<FournisseurAcces> findByRegulateur(Regulateur regulateur, Pageable pageable);
}
