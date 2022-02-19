package com.sfm.qoentum.service.admin;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Pays;

public interface PaysService {
	
	Pays save(Pays pays);
	
    List<Pays> findAll();
    
    void delete(long id);
    
    Pays findById(Long id);
    
    long count();
    
    EntityPage<Pays> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsPaysByNom(String nom, Long id);
    
}
